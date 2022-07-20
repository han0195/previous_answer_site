package kbuni_question.domain.exam;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="img")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImgEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int imgno;
    private String imgurl;

    @ManyToOne
    @JoinColumn(name = "pno")
    private ProblemEntity problemEntity;
}
