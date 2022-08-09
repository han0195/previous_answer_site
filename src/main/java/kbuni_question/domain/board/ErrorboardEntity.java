package kbuni_question.domain.board;

import kbuni_question.domain.member.MemberEntity;
import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;
import java.lang.reflect.Member;

@Entity
@Table(name="errorboard")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorboardEntity {
    @Id
    @GeneratedValue
    private int eno;
    private String econtent;
    private int Solution;

    @ManyToOne
    @JoinColumn(name = "mno")
    private MemberEntity memberEntity;


}
