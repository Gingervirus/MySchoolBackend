package backend.services.Impl;

import backend.domain.Absent;
import backend.repositories.AbsentRepository;
import backend.services.AbsentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by amwentzel on 2016/08/29.
 */
@Service
public class AbsentServiceImpl implements AbsentService{
    @Autowired
    private AbsentRepository absentRepository;

    @Override
    public Absent create(Absent user) {

        return absentRepository.save(user);
    }


    @Override
    public Absent readById(Long id) {

        return absentRepository.findOne(id);
    }

    @Override
    public Set<Absent> readAll() {
        Set<Absent> result = new HashSet<>();

        Iterator iterator = absentRepository.findAll().iterator();
        while (iterator.hasNext())
        {
            result.add((Absent) iterator.next());
        }
        return result;
    }

    @Override
    public Absent update(Absent entity) {
        return absentRepository.save(entity);
    }

    @Override
    public void delete(Absent account) {
        absentRepository.delete(account);
    }
}
