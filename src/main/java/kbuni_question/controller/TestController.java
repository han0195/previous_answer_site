package kbuni_question.controller;



import kbuni_question.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletResponse;

@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    TestService testService;

    //카테고리 list 가져오기 처리
    @GetMapping("/getcatlist")
    @ResponseBody
    public void getcatlist(ServletResponse response){
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.getWriter().print(testService.getcatlist());
        }catch (Exception e){
            System.out.println("카테고리가져오기 오류" + e);
        }
    }

}
