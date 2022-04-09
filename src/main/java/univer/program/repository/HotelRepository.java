package univer.program.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import univer.program.entity.Hotel;

import java.util.Collection;

public interface HotelRepository extends CrudRepository<Hotel, Integer> {
}
