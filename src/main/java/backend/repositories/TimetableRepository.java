package backend.repositories;

import backend.domain.Timetable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by amwentzel on 2016/08/29.
 */
@Repository
public interface TimetableRepository extends CrudRepository<Timetable, Long> {
}
