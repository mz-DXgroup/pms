package dxgroup.pms.domain;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class FileStore {
    @Id
    private Long id;

    private String fileName;
    private String originalName;
    private String Path;
    private String contentType;

    @JoinColumn(name = "person_id")
    @OneToOne(fetch = FetchType.LAZY)
    private Person person;

}
