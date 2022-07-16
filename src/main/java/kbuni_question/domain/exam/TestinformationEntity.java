package kbuni_question.domain.exam;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="testinformation")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TestinformationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tno;
    private String tname;
    private String tyear;
    private String tquarter;

    @ManyToOne
    @JoinColumn(name = "dno")
    private DepartmentEntity departmentEntity;

    @OneToMany(mappedBy = "testinformationEntity", cascade = CascadeType.ALL)
    @Builder.Default
    private List<ProblemEntity> problemEntityList = new ArrayList<>();

}
