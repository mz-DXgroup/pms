package dxgroup.pms.config;

import dxgroup.pms.jwt.CustomAuthenticationEntryPoint;
import dxgroup.pms.jwt.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    @Qualifier("customAuthenticationEntryPoint")
    private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    public static final String[] PERMIT_ALL = {
            "/api-docs/**", "/swagger-ui**", "/swagger-ui/**","/login", "/api/login", "/api/join"
    };

    public static final String[] AUTH_ALL = {
            "/api/**","api"
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors().and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and() // 이 부분을 추가해야 합니다.
                .authorizeRequests()
                .antMatchers(PERMIT_ALL).permitAll()
                .antMatchers(AUTH_ALL).authenticated()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling().authenticationEntryPoint(customAuthenticationEntryPoint);

        return http.build();
    }



    //cors 에러 추가
    @Bean
    public CorsConfigurationSource corsConfigrationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.addAllowedOrigin("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
