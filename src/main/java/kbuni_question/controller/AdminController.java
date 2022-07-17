package kbuni_question.controller;

import kbuni_question.dto.ExamDto;
import kbuni_question.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    ExamService examService;

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
    public void getdlist(HttpServletResponse response){
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
}
