package kbuni_question.domain.member;

import kbuni_question.domain.board.ReplyEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="member")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int mno;
    private String mid;
    private String mpassword;
    private String mname;
    private String findpwkey;

    @Enumerated(EnumType.STRING)
    private Role role;

    public String getrolekey() { return role.getKey(); }// 권한

    @OneToMany(mappedBy = "memberEntity", cascade = CascadeType.ALL)
    @Builder.Default
    private List<ReplyEntity> replyEntityList = new ArrayList<>();



}
