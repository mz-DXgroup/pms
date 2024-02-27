package dxgroup.pms.jwt.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import dxgroup.pms.domain.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class UserTokenInfo {
    private String userId;
    private String userNm;


    private String loadUserId;

    @JsonIgnore
    private String token;
    @JsonIgnore
    private String refreshToken;
    @JsonIgnore
    private Date issueDttm;
    @JsonIgnore
    private Date expireDttm;

    private Date updDttm;

    public UserTokenInfo() {
    }

    public UserTokenInfo(String userId, String userNm) {
        this.userId = userId;
        this.userNm = userNm;
        this.issueDttm = new Date();
    }

    public static UserTokenInfo from(Person person) {
        return new UserTokenInfo(person.getUserId(), person.getName());
    }
}
