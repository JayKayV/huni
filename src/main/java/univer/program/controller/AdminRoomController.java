package univer.program.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import univer.program.entity.Hotel;
import univer.program.entity.Room;
import univer.program.service.HotelService;
import univer.program.service.RoomService;

import java.util.Optional;

@Controller
@RequestMapping("/admin/room")
public class AdminRoomController {
    @Autowired
    private RoomService roomService;
    @Autowired
    private HotelService hotelService;

    public AdminRoomController(RoomService roomService, HotelService hotelService) {
        this.roomService = roomService;
        this.hotelService = hotelService;
    }

    @GetMapping("")
    public String getRoomList(@RequestParam("hid") int id , Model model) {
        Optional<Hotel> hotel = hotelService.find(id);

        if (hotel.isEmpty())
            return "redirect:/404";

        model.addAttribute("rooms", roomService.findByHotel(hotel.get()));
        return "roomList";
    }

    @GetMapping("/edit")
    public String editRoom(@RequestParam("id") int id, Model model) {
        Optional<Room> room = roomService.findById(id);

        if (room.isEmpty())
            return "redirect:/404";

        model.addAttribute("room", room.get());
        return "room_edit";
    }
}
