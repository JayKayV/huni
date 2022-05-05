package univer.program.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import univer.program.entity.Room;
import univer.program.entity.Hotel;
import univer.program.repository.RoomRepository;
import univer.program.service.RoomService;

import java.util.List;
import java.util.Optional;

@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomRepository roomRepository;

    public void add(Room room) {
        roomRepository.save(room);
    }

    public void save(Room room) {
        roomRepository.save(room);
    }

    public boolean deleteById(int id) {
        Optional<Room> room = roomRepository.findById(id);

        if (room.isEmpty())
            return false;

        roomRepository.delete(room.get());
        return true;
    }

    public List<Room> findByHotel(Hotel hotel) {
        return roomRepository.findByHotel(hotel);
    }

    public Optional<Room> findById(int id) {
        return roomRepository.findById(id);
    }
}
