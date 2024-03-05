package dxgroup.pms.domain;

import dxgroup.pms.common.AuditingEntity;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@RequiredArgsConstructor
@Getter
@Entity
public class Person extends AuditingEntity {

    @Id
    @Column(name = "person_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;
    private String email;
    private String userId;
    private String password;
    private String jobTitle;
    private String position;
    private String age;
    private String career;
    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;
    private Boolean isPartner;
    private Boolean isAvailable;
    private String phoneNo;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "person")
    private List<IndeAssignment> indeAssignments;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "person")
    private List<Organization> organizations;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "person")
    private List<PersonProject> personProjects;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "person", fetch = FetchType.LAZY)
    private FileStore fileStore;

//========연관관계 편의 메서드
    public void addIndeAssignments(IndeAssignment indeAssignment) {
        indeAssignments.add(indeAssignment);
        indeAssignment.setPerson(this);
    }

    public void addOrganization(Organization organization) {
        organizations.add(organization);
        organization.setPerson(this);
    }

    public void addPersonProject(PersonProject personProject) {
        personProjects.add(personProject);
        personProject.setPerson(this);
    }

    public Person(String name, String email, String userId, String password, String jobTitle, String position, String age, String career, Role role,boolean isPartner,boolean isAvailable,String phoneNo) {
        this.name = name;
        this.email = email;
        this.userId = userId;
        this.password = password;
        this.jobTitle = jobTitle;
        this.position = position;
        this.age = age;
        this.career = career;
        this.role = role;
        this.isPartner = isPartner;
        this.isAvailable = isAvailable;
        this.phoneNo = phoneNo;
    }
}
