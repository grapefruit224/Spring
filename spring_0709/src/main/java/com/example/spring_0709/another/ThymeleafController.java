package com.example.spring_0709.another;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ThymeleafController {

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("name", "good~~~~~");
        model.addAttribute("message", "진짜 개더워요.");
        model.addAttribute("titleName", "SpringBoot시작");
        model.addAttribute("url", "www.naver.com");
        model.addAttribute("realUrl", "www.naver.com");
        return  "index";
    }
}
