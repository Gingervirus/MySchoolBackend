package backend.services.Impl;

import backend.domain.Parent;
import backend.repositories.ParentRepository;
import backend.services.ParentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by amwentzel on 2016/08/29.
 */
@Service
public class ParentServiceImpl implements ParentService {
    @Autowired
    private ParentRepository parentRepository;

    @Override
    public Parent create(Parent user) {

        return parentRepository.save(user);
    }


    @Override
    public Parent readById(Long id) {

        return parentRepository.findOne(id);
    }

    @Override
    public Set<Parent> readAll() {
        Set<Parent> result = new HashSet<>();

        Iterator iterator = parentRepository.findAll().iterator();
        while (iterator.hasNext())
        {
            result.add((Parent) iterator.next());
        }
        return result;
    }

    @Override
    public Parent update(Parent entity) {
        return parentRepository.save(entity);
    }

    @Override
    public void delete(Parent account) {
        parentRepository.delete(account);
    }
}