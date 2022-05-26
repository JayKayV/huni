package univer.program.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import univer.program.entity.Hotel;
import univer.program.service.HotelService;

import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/admin/hotel")
public class AdminHotelController {
    private HotelService hotelService;

    @Autowired
    public AdminHotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping(value={"", "/"})
    public String welcome(Model model) {
        model.addAttribute("hotels", hotelService.findall());
        return "hotelList.html";
    }

    @GetMapping("/add")
    public String addHotelForm() {
        return "hotel_add.html";
    }

    @PostMapping("/save")
    public String addHotel(@RequestParam("image") MultipartFile multipartFile, @RequestParam("name") String name,
                           @RequestParam("country") String country, @RequestParam("city") String city,
                           @RequestParam("address") String address, @RequestParam("phone_number") String phone_number,
                           @RequestParam("description") String description) throws IOException {
        Hotel hotel = new Hotel(name, country, city, address, phone_number, description);
        hotel.setImage(multipartFile.getBytes());
        hotelService.save(hotel);
        return "redirect:/admin/hotel";
    }

    @GetMapping("/edit/{id}")
    public String editHotel(@PathVariable("id") int id, Model model) {
        Optional<Hotel> hotel = hotelService.find(id);

        if (hotel.isEmpty())
            return "404";

        model.addAttribute("hotel", hotel.get());
        return "hotel_edit.html";
    }

    @GetMapping("/deleteHotel/{id}")
    public String deleteHotel(@PathVariable("id") int id, Model model) {
        Hotel hotel = hotelService.find(id).get();

        model.addAttribute("hotel", hotel);
        return "hotel_edit.html#deleteHotel";
    }

    @GetMapping("/image")
    public String getImage(@RequestParam("name") String name) {
        return name;
    }

    @PostMapping(value="/save/{id}")
    public String saveHotel(@PathVariable("id") int id, @RequestParam("image") MultipartFile multipartFile,
                            @RequestParam("name") String name, @RequestParam("country") String country,
                            @RequestParam("city") String city, @RequestParam("address") String address,
                            @RequestParam("phone_number") String phone_number,
                            @RequestParam("description") String description
                            )
            throws IOException {
        Hotel hotel = hotelService.find(id).get();
        hotel.setData(name, country, city, address, phone_number, description);
        hotel.setImage(multipartFile.getBytes());
        hotelService.save(hotel);

        return "redirect:/admin/hotel";
    }

    @PostMapping(value="/delete/{id}")
    public String deleteHotel(@PathVariable("id") int id) {
        hotelService.deleteById(id);

        return "redirect:/admin/hotel";
    }
}
