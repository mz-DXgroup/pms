package dxgroup.pms.controller;

import dxgroup.pms.dto.request.JoinRequest;
import dxgroup.pms.dto.request.LoginRequest;
import dxgroup.pms.jwt.dto.JwsDto;
import dxgroup.pms.jwt.dto.UserTokenInfo;
import dxgroup.pms.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/join")
    public ResponseEntity<Void> join(JoinRequest joinRequest) {

        loginService.join(joinRequest);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<JwsDto> login(LoginRequest loginRequest) {
        JwsDto login = loginService.login(loginRequest);
        return ResponseEntity.ok(login);
    }
}
