package kbuni_question.controller;

import kbuni_question.dto.MemberDto;
import kbuni_question.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/member")
public class MemberController {

    @Autowired
    MemberService memberService;

    @GetMapping("/login")
    public String login(){
        return "/member/login";
    }

    /* 회원 가입 페이지 이동 */
    @GetMapping("/singup")
    public String movesignup(){
        return "/member/singup";
    }

    /* 회원 가입 처리 */
    @PostMapping("/singup")
    public void singup(MemberDto memberDto, HttpServletResponse response){
        boolean pass = memberService.singup(memberDto);
        try {
            if(pass){
                response.sendRedirect("/member/login");
            }else{
                response.sendRedirect("/error");
            }
        }catch (Exception e){
            System.out.println("회원가입에러" + e);
        }
    }

    /* 별명 중복체크 */
    @PostMapping("/selectmname")
    @ResponseBody
    public boolean selectmname(@RequestParam("mname") String mname){
        return memberService.selectmname(mname);
    }

}
