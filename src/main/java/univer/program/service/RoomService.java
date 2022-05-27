package univer.program.service;

import univer.program.entity.Hotel;
import univer.program.entity.Room;

import java.util.List;
import java.util.Optional;

public interface RoomService {
    void add(Room room);
    List<Room> findByHotel(Hotel hotel);
    boolean deleteById(int id);
    void save(Room room);
    Optional<Room> findById(int id);

    int countByType(String type);

    int minPriceByType(String type);

    List<Room> findByHotelAndType(Hotel hotel, String type);
}
