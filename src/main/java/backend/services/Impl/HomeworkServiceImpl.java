package backend.services.Impl;

import backend.domain.Homework;
import backend.repositories.HomeworkRepository;
import backend.services.HomeworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by amwentzel on 2016/08/29.
 */
@Service
public class HomeworkServiceImpl implements HomeworkService {
    @Autowired
    private HomeworkRepository homeworkRepository;

    @Override
    public Homework create(Homework user) {

        return homeworkRepository.save(user);
    }


    @Override
    public Homework readById(Long id) {

        return homeworkRepository.findOne(id);
    }

    @Override
    public Set<Homework> readAll() {
        Set<Homework> result = new HashSet<>();

        Iterator iterator = homeworkRepository.findAll().iterator();
        while (iterator.hasNext())
        {
            result.add((Homework) iterator.next());
        }
        return result;
    }

    @Override
    public Homework update(Homework entity) {
        return homeworkRepository.save(entity);
    }

    @Override
    public void delete(Homework account) {
        homeworkRepository.delete(account);
    }
}
