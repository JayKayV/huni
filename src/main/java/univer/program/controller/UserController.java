package univer.program.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import univer.program.entity.UserRole;
import univer.program.repository.UserRoleRepository;

@Controller
public class UserController {
    private UserRoleRepository userRoleRepository;

    @Autowired
    public UserController(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute(new UserRole());
        return "login";
    }
}
