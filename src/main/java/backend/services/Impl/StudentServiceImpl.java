package backend.services.Impl;

import backend.domain.Student;
import backend.repositories.StudentRepository;
import backend.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by amwentzel on 2016/08/29.
 */
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student create(Student user) {

        return studentRepository.save(user);
    }


    @Override
    public Student readById(Long id) {

        return studentRepository.findOne(id);
    }

    @Override
    public Set<Student> readAll() {
        Set<Student> result = new HashSet<>();

        Iterator iterator = studentRepository.findAll().iterator();
        while (iterator.hasNext())
        {
            result.add((Student) iterator.next());
        }
        return result;
    }

    @Override
    public Student update(Student entity) {
        return studentRepository.save(entity);
    }

    @Override
    public void delete(Student account) {
        studentRepository.delete(account);
    }
}