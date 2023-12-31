package hello.thymeleaf.basic;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
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

    /**
     * session - httpRequest, httpResponse 뿐만 아니라
     * 로그인 같을 걸 할 때 사용자가 웹 브라우저를 끄지 않는 이상
     * 계속 유지가 되는 것이다.
     * httpRequest는 유저가 들어왔다가 나가면 끝난다.
     * httpSession은 유저가 웹 브라우저를 종료하기 전까지는 계속 남아서 똑같은 데이터가 유지가 된다.
     */
    @GetMapping("/basic-objects")
    public String basicObjects(HttpSession session) {
        // sessionData에 접근하는 걸 알아보자
        // session에 setAttribute로 데이터를 담아두고 타임리프에서 꺼낼 수 있다.
        session.setAttribute("sessionData", "Hello Session");
        return "basic/basic-objects";
    }

    // basicObjects()를 위해서 스프링 빈 만들기
    // ComponentScan이 되어서 helloBean이 등록이 된다.
    @Component("helloBean")
    static class HelloBean {
        public String hello(String data) {
            return "Hello " + data;
        }
    }

    // 유틸리티 객체와 날짜
    @GetMapping("/date")
    public String date(Model model) {
        model.addAttribute("localDateTime", LocalDateTime.now()); // 현재 시간
        return "basic/date";
    }

    // URL 링크 ★중요★
    @GetMapping("/link")
    public String link(Model model) {
        model.addAttribute("param1", "data1");
        model.addAttribute("param2", "data2");
        return "basic/link";
    }

    // 리터럴(Literals)
    @GetMapping("/literal")
    public String literal(Model model) {
        model.addAttribute("data", "Spring!");
        return "basic/literal";
    }

    // 연산
    @GetMapping("/operation")
    public String operation(Model model) {
        model.addAttribute("nullData", null);
        model.addAttribute("data", "Spring!");
        return "basic/operation";
    }

    // 속성 값 설정 th:*
    @GetMapping("/attribute")
    public String attribute() {
        return "basic/attribute";
    }

    // 반복 th:each
    @GetMapping("/each")
    public String each(Model model) {
        addUsers(model);
        return "basic/each";
    }

    private void addUsers(Model model) {
        List<User> list = new ArrayList<>();
        list.add(new User("userA", 10));
        list.add(new User("userB", 20));
        list.add(new User("userC", 30));

        model.addAttribute("users", list);
    }

    // 조건부 평가
    @GetMapping("/condition")
    public String condition(Model model) {
        addUsers(model);
        return "basic/condition";
    }

    // 주석
    @GetMapping("/comments")
    public String comments(Model model) {
        model.addAttribute("data", "Spring!");
        return "basic/comments";
    }

    // 블록 th:block / HTML 태그가 아닌 타임리프의 유일한 자체 태그
    // 타임리프는 속성으로 동작하지 태그로 동작하지 않는다.
    // 해결하기 어려운 것들을 해결하기 위해서 이런 타임리프가 제공하는 자체 태그가 필요할 때가 있다.
    @GetMapping("/block")
    public String block(Model model) {
        addUsers(model);
        return "basic/block";
    }

    // 자바스크립트 인라인 th:inline="javascript"
    @GetMapping("/javascript")
    public String javascript(Model model) {
        model.addAttribute("user", new User("userA", 10)); // 유저 추가
        addUsers(model);

        return "basic/javascript";
    }
}
