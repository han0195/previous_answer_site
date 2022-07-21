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


    //시험 선택페이지이동
    @GetMapping("/ch")
    public String movechpage(@RequestParam("chmode") int chmode, HttpServletRequest request) {
        request.getSession().setAttribute("chmode", chmode);
        return "/exam/chexam";
    }

    @GetMapping("/oneexam")
    public String monveonexam(){
        return "/exam/oneexam";
    }

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

    // 시험시작 진입
    @PostMapping("/beforestart")
    public void beforestart(@RequestParam("tno") int tno, HttpServletRequest request, HttpServletResponse response){
        try {
            request.setCharacterEncoding("UTF-8");
            // 해당 모드
            int chmode = Integer.parseInt(request.getSession().getAttribute("chmode").toString());
            // test 번호 세션저장
            request.getSession().setAttribute("tno", tno);

            if(chmode == 1){ /* 한문제씩 푸는 모드라면*/
                response.setCharacterEncoding("UTF-8");
                response.getWriter().print("/exam/oneexam");
            }else if(chmode == 2){ /* 모아풀기 모드라면 */

            }else { /* 실전연습 모드라면 */

            }

        }catch (Exception e){
            System.out.println(e);
        }

    }



}
