package kbuni_question.dto;

import kbuni_question.domain.member.MemberEntity;
import kbuni_question.domain.member.Role;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberDto {
    private int mno;
    private String mid;
    private String mpassword;
    private String mname;
    private String findpwkey;

    
    //회원 entity 변환
    public MemberEntity membertoentity(){

        //패스워드암호화
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        return MemberEntity.builder()
                .mid(this.mid)
                .mpassword(passwordEncoder.encode(this.mpassword))
                .mname(this.mname)
                .findpwkey(this.findpwkey)
                .role(Role.MEMBER)
                .build();

    }

}
