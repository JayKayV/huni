package univer.program.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import univer.program.repository.RoomRepository;

@Controller
@RequestMapping("/hotel/admin")
public class AdminRoomController {
    @Autowired
    private RoomRepository roomRepo;

    public AdminRoomController(RoomRepository roomRepo) {
        this.roomRepo = roomRepo;
    }

    @GetMapping("/rooms")
    public String getRoomList(Model model) {
        model.addAttribute("rooms", roomRepo.findAll());
        return "roomList";
    }
}
