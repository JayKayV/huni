package univer.program.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import univer.program.entity.Hotel;
import univer.program.entity.Room;
import univer.program.service.HotelService;
import univer.program.service.RoomService;
import univer.program.validator.RoomValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Controller
@RequestMapping("/admin/room")
public class AdminRoomController {
    @Autowired
    private RoomService roomService;
    @Autowired
    private HotelService hotelService;
    @Autowired
    private RoomValidator roomValidator;

    public AdminRoomController(RoomService roomService, HotelService hotelService, RoomValidator roomValidator) {
        this.roomService = roomService;
        this.hotelService = hotelService;
        this.roomValidator = roomValidator;
    }

    @GetMapping("")
    public String getRoomList(@RequestParam("hid") int id , Model model) {
        Optional<Hotel> hotel = hotelService.find(id);

        if (hotel.isEmpty())
            return "redirect:/404";

        model.addAttribute("hid", id);
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

    @GetMapping("/add")
    public String addRoomFrm(@RequestParam("hid") int id, Model model) {
        Room room = new Room();
        model.addAttribute("hid", id);
        model.addAttribute("room", room);
        return "room_add";
    }

    @PostMapping(value={"/saveedit"})
    public RedirectView editRoom(@ModelAttribute("Room") Room room, BindingResult bindingResult
            , RedirectAttributes redirectAttributes) {
        roomValidator.validate(room, bindingResult);
        //room.setHotel(roomService.findById(room.getId()).get().getHotel());

        if (bindingResult.hasErrors()) {
            List<ObjectError> oe = bindingResult.getAllErrors();
            List<String> errorCodes = new ArrayList<String>();
            for (ObjectError o: oe)
                errorCodes.add(o.getCode());
            redirectAttributes.addAttribute("e", String.join(",", errorCodes));
            RedirectView rv = new RedirectView("/errors");
            return rv;
        }

        roomService.save(room);
        redirectAttributes.addAttribute("hid", room.getHotel().getId());
        return new RedirectView("/admin/room");
    }

    @PostMapping("/delete/{id}")
    public RedirectView deleteRoom(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        Optional<Room> room = roomService.findById(id);
        if (room.isEmpty())
            return new RedirectView("/404");

        roomService.deleteById(id);
        int hid = room.get().getHotel().getId();
        redirectAttributes.addAttribute("hid", hid);
        return new RedirectView("/admin/room");
    }

    @PostMapping("/save/{id}")
    public RedirectView addRoom(@ModelAttribute("room") Room room, BindingResult bindingResult,
                                @PathVariable("id") int hid, RedirectAttributes redirectAttributes) {
        room.setHotel(hotelService.find(hid).get());
        roomValidator.validate(room, bindingResult);

        if (bindingResult.hasErrors())
            return new RedirectView("/404");

        roomService.save(room);
        redirectAttributes.addAttribute("hid", hid);
        return new RedirectView("/admin/room");
    }
}
