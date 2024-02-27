package dxgroup.pms.jwt.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JwsDto {
    private String jws;
    private String ref;
}
