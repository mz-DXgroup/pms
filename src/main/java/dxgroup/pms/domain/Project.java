package dxgroup.pms.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@RequiredArgsConstructor
@Entity
public class Project {
    @Id
    @Column(name = "project_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String ProjectCode;
    private Phase phase;
    private  boolean isDelete;

    @OneToMany(mappedBy = "project" ,cascade = CascadeType.ALL  )
    private List<PersonProject> personProjects;

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

//    @OneToMany(mappedBy = "project",cascade = CascadeType.ALL)
//    private Assignment assignment;

    //======연관관계 편의 메서드=======
    public void addPersonProject(PersonProject personProject) {
        personProjects.add(personProject);
        personProject.setProject(this);
    }

}
