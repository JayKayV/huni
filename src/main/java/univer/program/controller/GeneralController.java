package univer.program.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GeneralController {
    @GetMapping("/admin404")
    public String adminError404() {
        return "404";
    }

    @GetMapping("/user404")
    public String userError404() {
        return "404";
    }

    @GetMapping("/errors")
    public String error(@RequestParam("e") String[] errors, Model model) {
        model.addAttribute("error", errors);
        return "errorPage";
    }
}
