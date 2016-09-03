package backend.services.Impl;

import backend.domain.Mark;
import backend.repositories.MarkRepository;
import backend.services.MarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by amwentzel on 2016/08/29.
 */
@Service
public class MarkServiceImpl implements MarkService {
    @Autowired
    private MarkRepository markRepository;

    @Override
    public Mark create(Mark user) {

        return markRepository.save(user);
    }


    @Override
    public Mark readById(Long id) {

        return markRepository.findOne(id);
    }

    @Override
    public Set<Mark> readAll() {
        Set<Mark> result = new HashSet<>();

        Iterator iterator = markRepository.findAll().iterator();
        while (iterator.hasNext())
        {
            result.add((Mark) iterator.next());
        }
        return result;
    }

    @Override
    public Mark update(Mark entity) {
        return markRepository.save(entity);
    }

    @Override
    public void delete(Mark account) {
        markRepository.delete(account);
    }
}