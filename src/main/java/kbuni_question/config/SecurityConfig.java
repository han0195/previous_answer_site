package kbuni_question.config;

import kbuni_question.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    //웹시큐리티 사용자지정
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests()
                .antMatchers("/**").permitAll()
                .and()
                .csrf()
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
                .ignoringAntMatchers("/member/selectmname");

    }

    //로그인 보안 서비스
    @Autowired
    MemberService memberService;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(memberService).passwordEncoder(new BCryptPasswordEncoder());
    }
}
