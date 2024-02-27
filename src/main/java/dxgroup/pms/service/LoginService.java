package dxgroup.pms.service;

import dxgroup.pms.domain.Person;
import dxgroup.pms.dto.request.JoinRequest;
import dxgroup.pms.dto.request.LoginRequest;
import dxgroup.pms.jwt.JwtTokenProvider;
import dxgroup.pms.jwt.dto.JwsDto;
import dxgroup.pms.jwt.dto.UserTokenInfo;
import dxgroup.pms.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final PersonRepository personRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    public void join(@Valid JoinRequest joinRequest) {
        String hashedPassword = passwordEncoder.encode(joinRequest.getPassword());
        Person person = joinRequest.toEntity(hashedPassword);
        try {
            personRepository.save(person);
            personRepository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("아이디 비밀번호 확인 필요");
        }
    }

    public JwsDto login(LoginRequest loginRequest) {

        Person person = personRepository.findByUserId(loginRequest.getUserId())
                .stream()
                .filter(i -> passwordEncoder.matches(loginRequest.getPassword(), i.getPassword()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않습니다"));

        return jwtTokenProvider.issue(UserTokenInfo.from(person));
    }
}
