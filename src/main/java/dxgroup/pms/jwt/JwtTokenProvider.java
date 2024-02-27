package dxgroup.pms.jwt;

import dxgroup.pms.jwt.dto.JwsDto;
import dxgroup.pms.jwt.dto.JwtResult;
import dxgroup.pms.jwt.dto.UserTokenInfo;
import io.jsonwebtoken.*;
import io.jsonwebtoken.lang.Maps;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.Key;
import java.util.Date;
import io.jsonwebtoken.jackson.io.JacksonDeserializer;
@Slf4j
@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    private final LogoutJwtCache logoutJwtCache;
    @Value("${jwt.secret-key}")
    private String SECRET_KEY;
    @Value("${jwt.refresh-valid-time}")
    private long REFRESH_VALID_TIME;

    private Key key;

    @PostConstruct
    protected void init() {
        key =  Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public Date refreshExpiration(Date issuedAt) {
        long exp = issuedAt.getTime() + REFRESH_VALID_TIME;
        return new Date(exp);
    }

    public JwsDto issue(UserTokenInfo user) {
        JwsDto jwsDto = new JwsDto();
        Date issuedAt = user.getIssueDttm();

        String jws = Jwts.builder()
                .setSubject(user.getUserId())
                .claim("user", user)
                .setIssuedAt(issuedAt)
                .setExpiration(user.getExpireDttm())
                .signWith(key)
                .compact();

        String ref = Jwts.builder()
                .setSubject("refresh token")
                .claim("userId", user.getUserId())
                .setIssuedAt(issuedAt)
                .setExpiration(refreshExpiration(issuedAt))
                .signWith(key)
                .compact();

        jwsDto.setJws(jws);
        jwsDto.setRef(ref);

        return jwsDto;
    }

    public JwtResult validate(String jws, String refreshToken) {
        JwtResult result = new JwtResult();
        try {
            if(jws != null) {
                @SuppressWarnings({"unchecked", "rawtypes"})
                Claims claims = Jwts.parserBuilder()
                        .deserializeJsonWith(new JacksonDeserializer(Maps.of("user", UserTokenInfo.class).build()))
                        .setSigningKey(key).build()
                        .parseClaimsJws(jws)
                        .getBody();
                UserTokenInfo userInfo = (UserTokenInfo) claims.get("user");
                result.setDmpUser(userInfo);

                validateTokenIsNotForALoggedOut(jws);
            } else {
                Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(refreshToken).getBody();
                String userId = (String) claims.get("userId");
                UserTokenInfo user = new UserTokenInfo();
                user.setUserId(userId);
                result.setDmpUser(user);

                validateTokenIsNotForALoggedOut(refreshToken);
            }

        } catch (ExpiredJwtException e) {
            log.warn("# Token expired - {}", e.getMessage());
            throw new JwtException(e.getMessage());
        } catch (SignatureException e) {
            log.warn("# Wrong signature - {}", e.getMessage());
            throw new JwtException(e.getMessage());
        } catch (IllegalArgumentException e) {
            log.warn("# no token : cannot be empty - {}", e.getMessage());
            throw new JwtException(e.getMessage());
        } catch (MalformedJwtException e) {
            log.warn("# Wrong token - {}", e.getMessage());
            throw new JwtException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            log.warn("# JWT token Parse Exception - {}", e.getMessage());
            throw new JwtException(e.getMessage());
        }
        return result;
    }

    private void validateTokenIsNotForALoggedOut(String token) {
        OnUserLogoutSuccessEvent previouslyLoggedOutEvent = logoutJwtCache.getLogoutEventForToken(token);
        if (previouslyLoggedOutEvent != null) {
            String userId = previouslyLoggedOutEvent.getUserId();
            Date logoutEventDate = previouslyLoggedOutEvent.getEventTime();
            String errorMessage = String.format("Token corresponds to an already logged out user [%s] at [%s]. Please login again", userId, logoutEventDate);
            throw new IllegalArgumentException(errorMessage);
        }
    }


}
