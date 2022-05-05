package univer.program.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookingController {
    @GetMapping(value={"","/"})
    public String welcome() {
        return "index";
    }
}
