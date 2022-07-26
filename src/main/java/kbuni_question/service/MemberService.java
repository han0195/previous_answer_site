package kbuni_question.service;

import kbuni_question.domain.member.MemberEntity;
import kbuni_question.domain.member.MemberRepository;
import kbuni_question.dto.LoginDto;
import kbuni_question.dto.MemberDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Member;
import java.util.Collections;
import java.util.Optional;

@Service
public class MemberService implements UserDetailsService {

    @Autowired
    MemberRepository memberRepository;
    
    //로그인
    @Override
    public UserDetails loadUserByUsername(String mid) throws UsernameNotFoundException {
        // 1. 회원 아이디로 엔티티 찾기
        Optional<MemberEntity> entityOptional =  memberRepository.findBymid( mid );
        MemberEntity memberEntity = entityOptional.orElse(null);
        System.out.println("gd");
        System.out.println(memberEntity);
        return new LoginDto(  memberEntity ,
                Collections.singleton(new SimpleGrantedAuthority(memberEntity.getrolekey())) );
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

        Optional<MemberEntity> memberEntity = memberRepository.findBymnames(mname);
            System.out.println(memberEntity);
        if(!memberEntity.isPresent()){ /* 동일데이터 존재 */
            System.out.println("존재x");
            return false;
        }else{ /* 동일데이터 존재 x */
            System.out.println("존재");
            return true;
        }

    }


}
