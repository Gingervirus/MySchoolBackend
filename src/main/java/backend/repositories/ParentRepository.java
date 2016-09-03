package backend.repositories;

import backend.domain.Parent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by amwentzel on 2016/08/29.
 */
@Repository
public interface ParentRepository extends CrudRepository<Parent, Long> {
}
