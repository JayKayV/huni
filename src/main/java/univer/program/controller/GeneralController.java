package univer.program.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GeneralController {
    @GetMapping("/404")
    public String error404() {
        return "404";
    }

    @GetMapping("/errors")
    public String error(@RequestParam("e") String[] errors, Model model) {
        model.addAttribute("error", errors);
        return "errorPage";
    }
}
