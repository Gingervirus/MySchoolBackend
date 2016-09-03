package backend.repositories;

import backend.domain.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by amwentzel on 2016/08/29.
 */
@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {
}
