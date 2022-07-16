package kbuni_question.domain.board;

import kbuni_question.domain.exam.ProblemEntity;
import kbuni_question.domain.member.MemberEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="reply")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReplyEntity {

    @Id
    @GeneratedValue
    private int rno;
    private int rindex;
    private String rcontent;

    @ManyToOne
    @JoinColumn(name = "pno")
    private ProblemEntity problemEntity;

    @ManyToOne
    @JoinColumn(name = "mno")
    private MemberEntity memberEntity;

}
