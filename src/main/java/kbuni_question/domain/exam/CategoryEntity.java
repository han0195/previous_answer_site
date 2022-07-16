package kbuni_question.domain.exam;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="categry")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cno;
    private String cname;

    @Builder.Default
    @OneToMany(mappedBy = "categoryEntity", cascade = CascadeType.ALL)
    private List<DepartmentEntity> departmentEntityList = new ArrayList<>();

}
