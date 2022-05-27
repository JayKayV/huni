package univer.program.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import univer.program.entity.Bill;
import univer.program.entity.Hotel;
import univer.program.entity.Room;
import univer.program.entity.User;
import univer.program.repository.BillRepository;
import univer.program.service.HotelService;
import univer.program.service.RoomService;
import univer.program.service.UserService;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

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
    public String getBookingFrm(@RequestParam("id") int id, Model model) {
        model.addAttribute("r_id", id);
        return "bookingFrm";
    }

    @PostMapping("/saveBooking")
    public String saveBooking(@RequestParam("id") int r_id, @RequestParam("name") String name,
                              @RequestParam("email") String email, @RequestParam("phone") String phone,
                              @RequestParam("address") String address , @RequestParam("total_adults") int adults,
                              @RequestParam("total_children") int childs, @RequestParam("checkin") Date checkin,
                              @RequestParam("checkout") Date checkout) {
        User user = new User(0, name, address, email, phone, null, "user");
        user = userService.save(user);
        Room room = roomService.findById(r_id).get();
        Set<Room> sr = new HashSet<Room>();
        sr.add(room);

        Bill bill = new Bill(checkin, checkout, adults + childs);
        bill.setUser(user);
        bill.setRoom(sr);
        billRepository.save(bill);
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


    @GetMapping("/rooms/{t}")
    public String roomList(@PathVariable("t") String type , @RequestParam("hid") int hid, Model model) {
        Hotel hotel = hotelService.find(hid).get();
        model.addAttribute("rooms", roomService.findByHotelAndType(hotel, type));
        return "rooms";
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
    public String getDetail(@PathVariable("id") int id, Model model) {
        Room room = roomService.findById(id).get();
        model.addAttribute("room", room);
        model.addAttribute("h", room.getHotel());
        return "details";
    }
}
