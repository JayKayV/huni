package univer.program.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import univer.program.service.UserService;

@Controller
@RequestMapping("/admin/user")
public class AdminUserController {
    @Autowired
    private UserService userService;

    public AdminUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public String getUserList(Model model) {
        model.addAttribute("users", userService.findall());
        return "userList";
    }

    @GetMapping("/edit")
    public String getUserEditFrm(@RequestParam("id") int id, Model model) {
        model.addAttribute("user", userService.findById(id).get());
        return "user_edit";
    }

    @PostMapping("/save")
    public String saveUserData() {
        return "redirect:/admin/user";
    }
}
