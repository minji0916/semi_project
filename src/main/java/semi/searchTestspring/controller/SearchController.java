package semi.searchTestspring.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;

@Controller
public class SearchController {

    @GetMapping("/requestParameter")
    public String requestParameter(
            @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
            @RequestParam("requestDateTime") LocalDateTime requestDateTime) {

        System.out.println("requestParameter 요청 데이터 = " + requestDateTime);

        return "requestParameter 성공";
    }

//    @GetMapping("/join")
//    public String join(Model model){
//        return "joinform"; //회원가입화면 joinform.html 을 보여조
//    }
//
//    @PostMapping("/join") id, phonnumber
//    public String joinRegist(dfasdf){
//        Servvieasd; // DB저장
//        return "redirect::/main"; // 메인 페이지로 이동
//    }

//    @GetMapping("hello")
//    public String hello(Model model){
//        model.addAttribute("data","hello!!");
//        return "hello";
//    }
//
//    @GetMapping("hello-mvc")
//    public String helloMvc(@RequestParam("name") String name, Model model) {
//        model.addAttribute("name", name);
//        return "hello-template";
//    }



//    @GetMapping("hello-api")
//    @ResponseBody
//    public Hello helloApi(@RequestParam("name") String name) {
//        Hello hello = new Hello();
//        hello.setName(name);
//        return hello;
//    }

//    static class Hello {
//        private String name;
//
//        public String getName() {
//            return name;
//        }
//
//        public void setName(String name) {
//            this.name = name;
//        }
//    }

}
