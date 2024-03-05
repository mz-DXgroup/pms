package dxgroup.pms.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@RequiredArgsConstructor
@Getter
@Entity
public class CustomerEmployee {
    @Id
    @Column(name = "customer_employee_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String jobTitle;
    private String position;
}
