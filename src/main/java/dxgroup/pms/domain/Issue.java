package dxgroup.pms.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
@RequiredArgsConstructor
@Getter
@Entity
public class Issue {

    @Id
    @Column(name = "issue_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
}
