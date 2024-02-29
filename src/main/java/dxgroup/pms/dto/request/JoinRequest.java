package dxgroup.pms.dto.request;

import dxgroup.pms.domain.Role;
import dxgroup.pms.domain.Person;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class JoinRequest {
    private final String name;
    private final String email;
    private final String userId;
    private final String password;
    private final String jobTitle;
    private final String position;
    private final String age;
    private final String career;
    private Role role = Role.USER;

    public Person toEntity(String hashedPassword) {
        return new Person(
                this.name,
                this.email,
                this.userId,
                hashedPassword,
                this.jobTitle,
                this.position,
                this.age,
                this.career,
                this.role);
    }
}
