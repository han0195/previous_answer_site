package kbuni_question.service;

import kbuni_question.domain.board.ErrorboardEntity;
import kbuni_question.domain.board.ErrorboardRepository;
import kbuni_question.domain.member.MemberEntity;
import kbuni_question.domain.member.MemberRepository;
import kbuni_question.dto.ErrorDto;
import kbuni_question.dto.LoginDto;
import kbuni_question.dto.MemberDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Member;
import java.util.Collections;
import java.util.Optional;

@Service
public class MemberService implements UserDetailsService {


    
    //로그인 서비스
    @Override
    public UserDetails loadUserByUsername(String mid) throws UsernameNotFoundException {
        // 1. 회원 아이디로 엔티티 찾기
        Optional<MemberEntity> entityOptional =  memberRepository.findBymid( mid );
        MemberEntity memberEntity = entityOptional.orElse(null);
        LoginDto loginDto =new LoginDto(  memberEntity ,
                Collections.singleton(new SimpleGrantedAuthority(memberEntity.getrolekey())) );
        return loginDto;
    }

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    HttpServletRequest request; // 세션 사용을 위한 request 객체 선언

    @Autowired
    ErrorboardRepository errorboardRepository;

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

    /* 오류 접수 */
    @Transactional
    public boolean inserterror(ErrorDto errorDto, int mno){

        MemberEntity memberEntity = memberRepository.findById(mno).get();

        ErrorboardEntity entity = errorDto.toentity();
        errorboardRepository.save(entity);

        // 오류 entity와 memberEntity join
        memberEntity.getErrorboardEntityList().add(entity);
        entity.setMemberEntity(memberEntity);

        return true;

    }


}
