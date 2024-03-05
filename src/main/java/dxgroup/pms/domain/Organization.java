package dxgroup.pms.domain;

import dxgroup.pms.common.AuditingEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Organization extends AuditingEntity {

    @Id
    @Column(name = "organization_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String name;
    private  boolean isPartner;
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    private Person person;
}
