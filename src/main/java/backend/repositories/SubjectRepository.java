package backend.repositories;

import backend.domain.Subject;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by amwentzel on 2016/08/29.
 */
@Repository
public interface SubjectRepository extends CrudRepository<Subject, Long> {
}
