package hello.thymeleaf.basic;

import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    // SpringEL
    @GetMapping("/variable")
    public String variable(Model model) {
        User userA = new User("userA", 10);
        User userB = new User("userB", 20);

        List<User> list = new ArrayList<>();
        list.add(userA);
        list.add(userB);

        Map<String, User> map = new HashMap<>();
        map.put("userA", userA);
        map.put("userB", userB);

        model.addAttribute("user", userA);
        model.addAttribute("users", list);
        model.addAttribute("userMap", map);

        return "basic/variable";
    }
    // variable() - 샘플 데이터를 위해서 User클래스를 내부에서 만들어서 쓰겠다.
    @Data
    static class User {
        private String username;
        private int age;

        public User(String username, int age) {
            this.username = username;
            this.age = age;
        }
    }
}
