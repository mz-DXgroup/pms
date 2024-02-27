package dxgroup.pms.dto.request;

import dxgroup.pms.domain.Role;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class LoginRequest {
    private final String userId;
    private final String password;
}
