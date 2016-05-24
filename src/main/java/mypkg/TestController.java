package mypkg;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
    @RequestMapping("/testList")
    public String test(Model model) {
        return "testList";
    }

    @RequestMapping("/testListByCourse")
    public String test2(Model model) {
        return "testListByCourse";
    }
}