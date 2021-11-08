package lt.task_13.task1_3.repositories;

import lt.task_13.task1_3.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    User save(User user);

    Iterable<User> findAll();

    User findById(int id);

    void deleteById(int id);

}
