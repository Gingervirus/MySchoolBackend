package backend.services.Impl;

import backend.domain.Timetable;
import backend.repositories.TimetableRepository;
import backend.services.TimetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by amwentzel on 2016/08/29.
 */
@Service
public class TimetableServiceImpl implements TimetableService {
    @Autowired
    private TimetableRepository timetableRepository;

    @Override
    public Timetable create(Timetable user) {

        return timetableRepository.save(user);
    }


    @Override
    public Timetable readById(Long id) {

        return timetableRepository.findOne(id);
    }

    @Override
    public Set<Timetable> readAll() {
        Set<Timetable> result = new HashSet<>();

        Iterator iterator = timetableRepository.findAll().iterator();
        while (iterator.hasNext())
        {
            result.add((Timetable) iterator.next());
        }
        return result;
    }

    @Override
    public Timetable update(Timetable entity) {
        return timetableRepository.save(entity);
    }

    @Override
    public void delete(Timetable account) {
        timetableRepository.delete(account);
    }
}
