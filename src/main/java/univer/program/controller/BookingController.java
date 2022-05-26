package univer.program.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import univer.program.entity.User;
import univer.program.entity.UserRole;

@Controller
public class BookingController {
    @GetMapping(value={"","/"})
    public String welcome() {
        return "index";
    }


}
