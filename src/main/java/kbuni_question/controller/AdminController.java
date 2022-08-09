package kbuni_question.controller;

import kbuni_question.domain.board.ErrorboardEntity;
import kbuni_question.dto.ErrorDto;
import kbuni_question.dto.ExamDto;
import kbuni_question.dto.LoginDto;
import kbuni_question.dto.ProblemDto;
import kbuni_question.service.ExamService;
import kbuni_question.service.MemberService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.session.ConcurrentSessionControlAuthenticationStrategy;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.session.ConcurrentSessionFilter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    ExamService examService;

    @Autowired
    MemberService memberService;


    //어드민 메인페이지 이동
    @GetMapping("/main")
    public String moveadminmain(){
        return "/admin/adminmain";
    }

    // 시험지 등록페이지 이동
    @GetMapping("/saveexam")
    public String move() {
        return "/admin/saveexam";
    }

    // 문제관리 페이지 이동
    @GetMapping("/pmanager")
    public String movepmanager(){
        return "/admin/exammanager";
    }
    
    // 문제관리의 사용할 tno 세션저장
    @PostMapping("/pmanager")
    @ResponseBody
    public boolean tnosesstion(HttpServletRequest request, @RequestParam("tno") int tno){
        try {
            request.setCharacterEncoding("UTF-8");
            request.getSession().setAttribute("tno", tno);
            return true;
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }

    // 시험지정보 등록처리
    //시험지 저장
    @PostMapping("/saveinfo")
    @ResponseBody
    public boolean insertexam(ExamDto examDto){
        return examService.saveexaminfo(examDto);
    }

    //학과 리스트 가져오기 처리
    @GetMapping("/getdlist")
    @ResponseBody
    public void getdlist(HttpServletResponse response, HttpServletRequest request){
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.getWriter().print(examService.getdlist());
        }catch (Exception e){
            System.out.println(e);
        }
    }


    // 시험정보리스트 가져오기 처리
    @GetMapping("/getexaminfolist")
    @ResponseBody
    public void getexaminfolist(HttpServletResponse response, @RequestParam("dno") int dno){
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.getWriter().print(examService.getexamlist(dno));
        }catch (Exception e){
            System.out.println(e);
        }
    }

    //시험정보 하나만 가져오기
    @PostMapping("/getinfo")
    public void getinfo(HttpServletResponse response, HttpServletRequest request){
        try {
            int tno = Integer.parseInt(request.getSession().getAttribute("tno").toString());
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.getWriter().print(examService.getinfo(tno));
        }catch (Exception e) {

        }
    }
    
    // 문제저장
    @PostMapping("/saveproblem")
    @ResponseBody
    public boolean saveproblem(HttpServletResponse response, HttpServletRequest request, ProblemDto problemDto){

        System.out.println(problemDto.toString());

        int tno = Integer.parseInt(request.getSession().getAttribute("tno").toString());
        return examService.saveproblem(problemDto, tno);
    }

    @DeleteMapping("/pdelete")
    @ResponseBody
    // 문제삭제
    public boolean pdelete(@RequestParam("pno") int pno){
        return examService.pdelete(pno);
    }

    // 문제수정
    @PostMapping("/setproblem")
    @ResponseBody
    public boolean setproblem(ProblemDto problemDto) {
        return examService.setproblem(problemDto);
    }

    //오류 접수
    @PostMapping("/inserterror")
    @ResponseBody
    public boolean inserterror(ErrorDto entity){
        String pass = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(pass);
        if(pass.equals("anonymousUser")){ /* 로그인 아닐시 */
            return false;
        }else{ /* 로그인 */
            // 회원 번호 빼오기
            LoginDto loginDto = (LoginDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            int mno = loginDto.getMno();
            // 서비스 호출
            memberService.inserterror(entity, mno);
            return true;
        }

    }


}
