package dxgroup.pms.jwt.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class JwtResult {
    private LocalDateTime timestamp;
    private Integer status;
    private String error;
    private String message;
    private boolean valid;
    private UserTokenInfo dmpUser;
}
