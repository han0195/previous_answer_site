package kbuni_question.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class TestController {

    //시험선택 페이지 이동
    @GetMapping("selectionview")
    public String selectionview(){return "selectionview";}

}
