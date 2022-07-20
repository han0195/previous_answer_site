package kbuni_question.controller;

import kbuni_question.dto.ExamDto;
import kbuni_question.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/exam")
public class ExamController {

    @Autowired
    ExamService examService;


    @PostMapping("/getproblemlist")
    //문제리스트 가져오기
    public void getproblemlist(HttpServletResponse response, HttpServletRequest request){

        try {
            int tno = Integer.parseInt(request.getSession().getAttribute("tno").toString());
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.getWriter().print(examService.getproblemlist(tno));
        }catch (Exception e){
            System.out.println("여기인가?" + e);
        }

    }


    //한문제 가져오기
    @PostMapping("/getproblem")
    public void getproblem(HttpServletResponse response, @RequestParam("pno") int pno){

        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.getWriter().print(examService.getproblem(pno));
        }catch (Exception e){
            System.out.println(e);
        }


    }

}
