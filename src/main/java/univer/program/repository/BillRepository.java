package univer.program.repository;

import org.springframework.data.repository.CrudRepository;
import univer.program.entity.Bill;
import univer.program.entity.User;

import java.util.List;

public interface BillRepository extends CrudRepository<Bill, Integer> {
    List<Bill> findByUser(User user);
}
