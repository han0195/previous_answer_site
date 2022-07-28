package kbuni_question.config;

import kbuni_question.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    //웹시큐리티 사용자지정
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests()
                .antMatchers("/**").permitAll()
                .and()
                .formLogin()
                .loginPage("/member/login")
                .loginProcessingUrl("/member/logincontroller") // 로그일 처리할 URL 정의 -> loadUserByUsername
                .defaultSuccessUrl("/")
                .usernameParameter("mid")
                .passwordParameter("mpassword")
                .failureUrl("/member/login/error")
                .and()
                .logout()
                .logoutRequestMatcher( new AntPathRequestMatcher("/member/logout")) // 로그인 처리할 URL 정의
                .logoutSuccessUrl("/")
                .invalidateHttpSession( true ) // 세션 초기화
                .and()
                .csrf()
                .ignoringAntMatchers("/member/logincontroller") // 로그인
                .ignoringAntMatchers("/member/login")
                .ignoringAntMatchers("/admin/saveinfo")
                .ignoringAntMatchers("/admin/pmanager")
                .ignoringAntMatchers("/admin/getinfo")
                .ignoringAntMatchers("/admin/saveproblem")
                .ignoringAntMatchers("/exam/getproblemlist")
                .ignoringAntMatchers("/admin/pdelete")
                .ignoringAntMatchers("/exam/getproblem")
                .ignoringAntMatchers("/admin/setproblem")
                .ignoringAntMatchers("/exam/beforestart")
                .ignoringAntMatchers("/member/singup")
                .ignoringAntMatchers("/member/selectmname")
                .and()
                .exceptionHandling() // 오류페이지 발생시 시큐리티 페이지 전환
                .accessDeniedPage("/error");

    }

    //로그인 보안 서비스
    @Autowired
    MemberService memberService;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(memberService).passwordEncoder(new BCryptPasswordEncoder());
    }
}
