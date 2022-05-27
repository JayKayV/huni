package univer.program.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import univer.program.entity.Hotel;

import java.util.Collection;
import java.util.List;

public interface HotelRepository extends CrudRepository<Hotel, Integer> {
    @Query("SELECT h FROM Hotel h WHERE h.city = ?1 ORDER BY h.id DESC")
    List<Hotel> findByCity(String city);

    Hotel findTopByOrderByIdAsc();
}
