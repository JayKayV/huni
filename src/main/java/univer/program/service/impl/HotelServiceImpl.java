package univer.program.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import univer.program.entity.Hotel;
import univer.program.service.HotelService;
import univer.program.repository.HotelRepository;

import java.util.List;
import java.util.Optional;

@Service
public class HotelServiceImpl implements HotelService {
    private HotelRepository hotelRepo;

    @Autowired
    public HotelServiceImpl(HotelRepository hotelRepo) {
        this.hotelRepo = hotelRepo;
    }

    public Optional<Hotel> find(int id) {
        return hotelRepo.findById(id);
    }

    public void add(Hotel hotel) {
        hotelRepo.save(hotel);
    }

    public boolean deleteById(int id) {
        Optional<Hotel> hotel = find(id);

        if (hotel.isEmpty())
            return false;

        hotelRepo.deleteById(id);
        return true;
    }

    @Override
    public Hotel getFirstHotel() {
        return hotelRepo.findTopByOrderByIdAsc();
    }

    public List<Hotel> findall() {
        return (List<Hotel>)hotelRepo.findAll();
    }

    public void save(Hotel hotel) {
        hotelRepo.save(hotel);
    }
}
