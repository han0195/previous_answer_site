package kbuni_question.domain.exam;


import kbuni_question.domain.board.ReplyEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="problem")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProblemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pno;
    private int pindex;
    private String panswer;
    private String pname;
    private String poption;

    @ManyToOne
    @JoinColumn(name = "tno")
    TestinformationEntity testinformationEntity;

    @OneToMany(mappedBy = "problemEntity", cascade = CascadeType.ALL)
    @Builder.Default
    private List<ReplyEntity> replyEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "problemEntity", cascade = CascadeType.ALL)
    @Builder.Default
    private List<ImgEntity> imgEntityList = new ArrayList<>();
}
