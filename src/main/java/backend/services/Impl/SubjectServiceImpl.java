package backend.services.Impl;

import backend.domain.Subject;
import backend.repositories.SubjectRepository;
import backend.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by amwentzel on 2016/08/29.
 */
@Service
public class SubjectServiceImpl implements SubjectService {
    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public Subject create(Subject user) {

        return subjectRepository.save(user);
    }


    @Override
    public Subject readById(Long id) {

        return subjectRepository.findOne(id);
    }

    @Override
    public Set<Subject> readAll() {
        Set<Subject> result = new HashSet<>();

        Iterator iterator = subjectRepository.findAll().iterator();
        while (iterator.hasNext())
        {
            result.add((Subject) iterator.next());
        }
        return result;
    }

    @Override
    public Subject update(Subject entity) {
        return subjectRepository.save(entity);
    }

    @Override
    public void delete(Subject account) {
        subjectRepository.delete(account);
    }
}