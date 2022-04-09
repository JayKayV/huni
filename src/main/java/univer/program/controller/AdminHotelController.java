package univer.program.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import univer.program.service.HotelService;

@Controller
public class AdminHotelController {
    private HotelService hotelService;

    @Autowired
    public AdminHotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping("/admin/hotel")
    public String welcome(Model model) {
        model.addAttribute("hotels", hotelService.findall());
        return "index.html";
    }

    @GetMapping("/admin/hotel/add")
    public String addHotel(Model model) {
        return
    }
}
