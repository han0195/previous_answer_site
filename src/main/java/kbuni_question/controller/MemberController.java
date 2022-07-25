package kbuni_question.controller;

import kbuni_question.dto.MemberDto;
import kbuni_question.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/member")
public class MemberController {

    @Autowired
    MemberService memberService;

    /* 회원 가입 페이지 이동 */
    @GetMapping("/singup")
    public String movesignup(){
        return "/member/singup";
    }

    /* 회원 가입 처리 */
    @PostMapping("/singup")
    public boolean singup(MemberDto memberDto){
        return memberService.singup(memberDto);
    }

    /* 별명 중복체크 */
    @PostMapping("/selectmname")
    @ResponseBody
    public boolean selectmname(@RequestParam("mname") String mname){
        return memberService.selectmname(mname);
    }

}
