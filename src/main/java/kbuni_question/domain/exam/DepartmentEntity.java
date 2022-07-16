package kbuni_question.domain.exam;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="departement")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int dno;
    private String dname;

    @ManyToOne
    @JoinColumn(name = "cno")
    private CategoryEntity categoryEntity;

    @OneToMany(mappedBy = "departmentEntity", cascade = CascadeType.ALL)
    @Builder.Default
    private List<TestinformationEntity> testinformationEntityList = new ArrayList<>();

}
