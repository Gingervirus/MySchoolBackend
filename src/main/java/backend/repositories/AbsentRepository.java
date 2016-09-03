package backend.repositories;

import backend.domain.Absent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by amwentzel on 2016/08/29.
 */
@Repository
public interface AbsentRepository extends CrudRepository<Absent, Long> {
}
