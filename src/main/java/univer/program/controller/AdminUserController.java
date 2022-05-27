package univer.program.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import univer.program.entity.User;
import univer.program.service.UserService;

@Controller
@RequestMapping("/admin/user")
public class AdminUserController {
    private final UserService userService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public AdminUserController(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userService = userService;
    }

    @GetMapping("")
    public String getUserList(Model model) {
        model.addAttribute("users", userService.findall());
        return "userList";
    }

    @GetMapping("/add")
    public String getUserAddFrm(Model model) {
        model.addAttribute(new User());
        return "user_add";
    }

    @GetMapping("/edit")
    public String getUserEditFrm(@RequestParam("id") int id, Model model) {
        model.addAttribute("user", userService.findById(id).get());
        return "user_edit";
    }

    @PostMapping(value={"/save", "/saveedit"})
    public String saveUserData(@ModelAttribute("user") User u) {
        u.setPassword(bCryptPasswordEncoder.encode(u.getPassword()));
        userService.save(u);
        return "redirect:/admin/user";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam("id") int id) {
        userService.deleteById(id);

        return "redirect:/admin/user";
    }
}
