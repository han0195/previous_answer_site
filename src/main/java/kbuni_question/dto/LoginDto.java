package kbuni_question.dto;

import kbuni_question.domain.member.MemberEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Getter
@Setter
public class LoginDto implements UserDetails {

    private int mno;        // 회원번호
    private String mid; // 회원아이디
    private String mpassword;// 회원비밀번호
    private String mname; // 회원 이름
    private final Set<GrantedAuthority> authorities;    // 부여된 인증들의 권한

    public LoginDto(MemberEntity memberEntity , Collection< ? extends GrantedAuthority> authorityList ) {
        this.mno = memberEntity.getMno();
        this.mid = memberEntity.getMid();
        this.mpassword = memberEntity.getMpassword();
        this.mname = memberEntity.getMname();
        this.authorities = Collections.unmodifiableSet( new LinkedHashSet<>( authorityList ));
    }

    // 패스워드 반환
    @Override
    public String getPassword() {
        return this.mpassword;
    }

    // 아이디 반환
    @Override
    public String getUsername() {
        return this.mid;
    }

    // 계정 인증 만료 여부 확인 [ true : 만료되지 않음 ]
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 계정 잠겨 있는지 확인 [ true : 잠겨있지 않음 ]
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 계정 패스워드가 만료 여부 확인 [ true : 만료 되지않음 ]
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 계정 사용 가능한 여부 확인 [ true : 사용가능 ]
    @Override
    public boolean isEnabled() {
        return true;
    }
}
