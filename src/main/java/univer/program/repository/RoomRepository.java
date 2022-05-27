package univer.program.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import univer.program.entity.Hotel;
import univer.program.entity.Room;

import java.util.List;

public interface RoomRepository extends CrudRepository<Room, Integer> {
    List<Room> findByHotel(Hotel hotel);

    int countByType(String type);

    @Query(value = "select MIN(r.price) FROM Room r WHERE r.type = ?1")
    int minPriceByType(String type);
}
