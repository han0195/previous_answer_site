package kbuni_question.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tcategory")
public class TcategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tcno; // 시험지카테고리번호
    private String tcname; // 카테고리제목

    @OneToMany(mappedBy = "tcategoryEntity", cascade = CascadeType.ALL)
    @Builder.Default
    private List<TestEntity> testEntities = new ArrayList<>(); // 시험지리스트
}
