package backend.repositories;

import backend.domain.Mark;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by amwentzel on 2016/08/29.
 */
@Repository
public interface MarkRepository extends CrudRepository<Mark, Long> {
}
