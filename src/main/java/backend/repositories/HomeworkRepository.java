package backend.repositories;

import backend.domain.Homework;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by amwentzel on 2016/08/29.
 */
@Repository
public interface HomeworkRepository extends CrudRepository<Homework, Long> {
}
