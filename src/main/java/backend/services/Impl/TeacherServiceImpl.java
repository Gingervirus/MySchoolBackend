package backend.services.Impl;

import backend.domain.Teacher;
import backend.repositories.TeacherRepository;
import backend.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by amwentzel on 2016/08/29.
 */
@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public Teacher create(Teacher user) {

        return teacherRepository.save(user);
    }


    @Override
    public Teacher readById(Long id) {

        return teacherRepository.findOne(id);
    }

    @Override
    public Set<Teacher> readAll() {
        Set<Teacher> result = new HashSet<>();

        Iterator iterator = teacherRepository.findAll().iterator();
        while (iterator.hasNext())
        {
            result.add((Teacher) iterator.next());
        }
        return result;
    }

    @Override
    public Teacher update(Teacher entity) {
        return teacherRepository.save(entity);
    }

    @Override
    public void delete(Teacher account) {
        teacherRepository.delete(account);
    }
}