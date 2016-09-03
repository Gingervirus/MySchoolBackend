package backend.services.Impl;

import backend.domain.Detention;
import backend.repositories.DetentionRepository;
import backend.repositories.UsersRepository;
import backend.services.DetentionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by amwentzel on 2016/08/29.
 */
@Service
public class DetentionServiceImpl implements DetentionService{
    @Autowired
    private DetentionRepository detentionRepository;

    @Override
    public Detention create(Detention user) {

        return detentionRepository.save(user);
    }


    @Override
    public Detention readById(Long id) {

        return detentionRepository.findOne(id);
    }

    @Override
    public Set<Detention> readAll() {
        Set<Detention> result = new HashSet<>();

        Iterator iterator = detentionRepository.findAll().iterator();
        while (iterator.hasNext())
        {
            result.add((Detention) iterator.next());
        }
        return result;
    }

    @Override
    public Detention update(Detention entity) {
        return detentionRepository.save(entity);
    }

    @Override
    public void delete(Detention account) {
        detentionRepository.delete(account);
    }
}
