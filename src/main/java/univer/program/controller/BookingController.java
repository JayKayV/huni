package univer.program.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import univer.program.entity.Hotel;
import univer.program.entity.Room;
import univer.program.entity.User;
import univer.program.repository.BillRepository;
import univer.program.service.HotelService;
import univer.program.service.RoomService;
import univer.program.service.UserService;

@Controller
public class BookingController {
    private final BillRepository billRepository;
    private final UserService userService;
    private final RoomService roomService;
    private final HotelService hotelService;

    public BookingController(BillRepository billRepository, UserService userService, RoomService roomService, HotelService hotelService) {
        this.billRepository = billRepository;
        this.userService = userService;
        this.roomService = roomService;
        this.hotelService = hotelService;
    }

    @ModelAttribute
    public void addSomeAttrs(Model model) {
        model.addAttribute("hotels", hotelService.findall());
    }

    @GetMapping(value={"","/"})
    public String welcome() {
        int id = hotelService.getFirstHotel().getId();
        return String.format("redirect:/hotel/%d", id);
    }

    @GetMapping("/booking")
    public String getBookingFrm(Model model) {
        model.addAttribute("user", new User());
        return "bookingFrm";
    }

    @PostMapping("/booking")
    public String saveBooking(Model model) {
        return "redirect:/success";
    }

    @GetMapping("/success")
    public String successPage(Model model) {
        return "success";
    }

    @GetMapping("/about")
    public String about(Model model) {
        return "about";
    }

    @GetMapping("/hotel/{id}")
    public String homePage(@PathVariable("id") int id, Model model) {
        Hotel hotel = hotelService.find(id).get();
        model.addAttribute("rooms", roomService.findByHotel(hotel));
        model.addAttribute("h", hotel);

        model.addAttribute("spMinPrice", roomService.minPriceByType("special"));
        model.addAttribute("vpMinPrice", roomService.minPriceByType("vip"));
        model.addAttribute("nmMinPrice", roomService.minPriceByType("normal"));

        return "index";
    }

    @GetMapping("/room/{id}")
    public String detail(@PathVariable("id") int id, Model model) {
        Room room = roomService.findById(id).get();
        model.addAttribute("room", room);
        model.addAttribute("h", room.getHotel());
        return "details";
    }
}
