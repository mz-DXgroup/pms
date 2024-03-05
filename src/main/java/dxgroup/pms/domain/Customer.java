package dxgroup.pms.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@RequiredArgsConstructor
@Entity
public class Customer {

    @Id
    @Column(name = "customer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String bizNum;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Project> project;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<CustomerEmployee> customerEmployees;
    
    //연관관계 편의 메소드 필요한지 확인 필요
}
