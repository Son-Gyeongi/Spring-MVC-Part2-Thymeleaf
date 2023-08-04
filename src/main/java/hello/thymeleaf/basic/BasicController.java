package hello.thymeleaf.basic;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/basic")
public class BasicController {

    @GetMapping("/text-basic")
    public String textBasic(Model model) {
        // 타입리프 뷰 템플릿에 데이터를 넘긴다.
        model.addAttribute("data", "Hello Spring!");
        return "basic/text-basic"; // 타입리프 뷰 템플릿
    }

    // 문자열에서 escape, unescape 처리
    @GetMapping("/text-unescaped")
    public String textUnescaped(Model model) {
        // 타입리프 뷰 템플릿에 데이터를 넘긴다.
        model.addAttribute("data", "Hello <b>Spring!</b>");
        return "basic/text-unescaped"; // 타입리프 뷰 템플릿
    }
}
