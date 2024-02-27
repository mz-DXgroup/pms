package dxgroup.pms.jwt;


import dxgroup.pms.jwt.dto.LogoutRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.ApplicationEvent;

import java.time.Instant;
import java.util.Date;

@Getter
@Setter
@ToString
public class OnUserLogoutSuccessEvent extends ApplicationEvent {

    private static final long serialVersionUID = 1L;

    private final String userId;
    private final String token;
    private final transient LogoutRequest logoutRequest;
    private final Date eventTime;

    public OnUserLogoutSuccessEvent(String userId, String token, LogoutRequest logoutRequest) {
        super(userId);
        this.userId = userId;
        this.token = token;
        this.logoutRequest = logoutRequest;
        this.eventTime = Date.from(Instant.now());
    }

}
