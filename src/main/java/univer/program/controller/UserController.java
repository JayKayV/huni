package univer.program.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import univer.program.entity.User;
import univer.program.entity.UserRole;
import univer.program.repository.UserRoleRepository;
import univer.program.service.UserService;

@Controller
public class UserController {
    private UserRoleRepository userRoleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserService userService;

    @Autowired
    public UserController(UserRoleRepository userRoleRepository,
                          BCryptPasswordEncoder bCryptPasswordEncoder, UserService userService) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRoleRepository = userRoleRepository;
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute(new UserRole());
        return "login";
    }

    @GetMapping("/signup")
    public String signupPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/signup")
    public String register(@ModelAttribute("user") User u, BindingResult bindingResult) {
        //userValidator.validate(u, bindingResult);

        if (bindingResult.hasErrors())
            return "signup";

        UserRole ur = u.getUserRole();

        ur.setId(u.getId());
        ur.setPassword(bCryptPasswordEncoder.encode(ur.getPassword()));
        userRoleRepository.save(ur);

        u.setUserRole(ur);
        userService.login(ur.getUsername(), ur.getPassword());
        return "redirect:/index";
    }
}
