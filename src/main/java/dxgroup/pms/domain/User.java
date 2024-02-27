package dxgroup.pms.domain;

import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@RequiredArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String userid;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role= Role.USER;

}
