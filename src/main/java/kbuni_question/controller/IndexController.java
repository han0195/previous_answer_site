package kbuni_question.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")        // "/" 최상위 경로
    public String index( ){
        return "main";
    }

    @GetMapping("/error")
    public String error(){
        return "error";
    }

}
