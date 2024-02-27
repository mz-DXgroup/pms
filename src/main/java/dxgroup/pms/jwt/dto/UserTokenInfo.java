package dxgroup.pms.jwt.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class UserTokenInfo {
    private String userId;
    private String userNm;
    private Date regDttm;
    private Date updDttm;
    private String loadUserId;

    @JsonIgnore
    private String token;
    @JsonIgnore
    private String refreshToken;
    @JsonIgnore
    private Date issueDttm;
    @JsonIgnore
    private Date expireDttm;
}
