package univer.program.service;

import univer.program.entity.Hotel;

import java.util.List;
import java.util.Optional;
public interface HotelService {
    Optional<Hotel> find(int id);
    List<Hotel> findall();
    void add(Hotel hotel);
    void save(Hotel hotel);
    boolean deleteById(int id);

    Hotel getFirstHotel();
}
