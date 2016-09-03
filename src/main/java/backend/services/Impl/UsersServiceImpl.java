package backend.services.Impl;

import backend.domain.Users;
import backend.repositories.UsersRepository;
import backend.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by amwentzel on 2016/08/29.
 */
@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public Users create(Users user) {

        return usersRepository.save(user);
    }


    @Override
    public Users readById(Long id) {

        return usersRepository.findOne(id);
    }

    @Override
    public Set<Users> readAll() {
        Set<Users> result = new HashSet<>();

        Iterator iterator = usersRepository.findAll().iterator();
        while (iterator.hasNext())
        {
            result.add((Users) iterator.next());
        }
        return result;
    }

    @Override
    public Users update(Users entity) {
        return usersRepository.save(entity);
    }

    @Override
    public void delete(Users account) {
         usersRepository.delete(account);
    }
}