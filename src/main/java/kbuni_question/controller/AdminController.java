package kbuni_question.controller;
import kbuni_question.dto.TestDto;
import kbuni_question.service.AdminService;
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
    AdminService adminService;

    // 어드민페이지/시험지등록 이동
    @GetMapping("/adminpage")
    public String adminpage(){
        return "admin/adminpage";
    }

    // 시험지등록처리
    @PostMapping("/inserttest")
    @ResponseBody
    public boolean inserttest(TestDto testDto){
        System.out.println("시험지 Dto 확인: " + testDto.toString());
        return adminService.inserttest(testDto);
    }

}
