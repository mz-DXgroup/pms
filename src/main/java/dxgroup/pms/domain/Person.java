package dxgroup.pms.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@RequiredArgsConstructor
@Getter
@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;
    private String email;
    private String userId;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;

    public Person(String name, String email, String userId, String password, Role role) {
        this.name = name;
        this.email = email;
        this.userId = userId;
        this.password = password;
        this.role = role;
    }
}
