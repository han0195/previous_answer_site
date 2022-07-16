package kbuni_question.controller;

import kbuni_question.dto.ExamDto;
import kbuni_question.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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


}
