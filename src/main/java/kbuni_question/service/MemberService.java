package kbuni_question.service;

import kbuni_question.domain.member.MemberEntity;
import kbuni_question.domain.member.MemberRepository;
import kbuni_question.dto.MemberDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberService implements UserDetailsService {

    @Autowired
    MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String mid) throws UsernameNotFoundException {
        return null;
    }

    /* 회원가입 처리 */
    @Transactional
    public boolean singup(MemberDto memberDto){
        MemberEntity memberEntity = memberDto.membertoentity();
        memberRepository.save(memberEntity);
        return true;
    }

    /* 별명 비교 */
    public boolean selectmname(String mname){
        MemberEntity memberEntity = memberRepository.findBymname(mname).get();
        if(memberEntity != null){ /* 동일데이터 존재 */
            return false;
        }else{ /* 동일데이터 존재 x */
            return true;
        }

    }


}
