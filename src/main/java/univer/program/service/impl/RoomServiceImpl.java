package univer.program.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import univer.program.entity.Room;
import univer.program.entity.Hotel;
import univer.program.repository.RoomRepository;
import univer.program.service.RoomService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomRepository roomRepository;

    public final void add(Room room) {
        roomRepository.save(room);
    }

    public final void save(Room room) {
        roomRepository.save(room);
    }

    public final boolean deleteById(int id) {
        Optional<Room> room = roomRepository.findById(id);

        if (room.isEmpty())
            return false;

        roomRepository.delete(room.get());
        return true;
    }

    public final List<Room> findByHotel(Hotel hotel) {
        return roomRepository.findByHotel(hotel);
    }

    public final Optional<Room> findById(int id) {
        return roomRepository.findById(id);
    }

    @Override
    public int countByType(String type) {
        return roomRepository.countByType(type);
    }

    @Override
    public int minPriceByType(String type) {
        return roomRepository.minPriceByType(type);
    }

    @Override
    public List<Room> findByHotelAndType(Hotel hotel, String type) {
        List<Room> rooms = findByHotel(hotel), roomsResult = new ArrayList<>();
        for (Room r: rooms) {
            if (r.getType().equals(type))
                roomsResult.add(r);
        }
        return roomsResult;
    }
}
