package backend.client;

import backend.domain.Users;
import backend.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Set;

/**
 * Created by amwentzel on 2016/08/29.
 */
@RestController
public class UsersController {
    // Inject Service
    @Autowired
    private UsersService accountService;

    //-------------------Create a User--------------------------------------------------------

    @RequestMapping(value = "/login/", method = RequestMethod.POST)
    public ResponseEntity<Users> createStudentAccount(@RequestBody Users studentAccount, UriComponentsBuilder ucBuilder) {

        Users studAccount = accountService.create(studentAccount);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/login/{id}").buildAndExpand(studentAccount.getUser_id()).toUri());

        return new ResponseEntity<>(studAccount, HttpStatus.CREATED);

    }

    //-------------------Retrieve Single USer--------------------------------------------------------
    @RequestMapping(value = "/login/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Users> getStudentAccount(@PathVariable("id") long id) {

        Users studentAccount = accountService.readById(id);

        if (studentAccount == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(studentAccount, HttpStatus.OK);

    }


    //-------------------Retrieve All Users--------------------------------------------------------

    @RequestMapping(value = "/logins/", method = RequestMethod.GET)

    public ResponseEntity<Set<Users>> getStudentAccounts() {

        Set<Users> studentAccounts = accountService.readAll();
        if(studentAccounts.isEmpty()){
            return new ResponseEntity<Set<Users>>(HttpStatus.NO_CONTENT);// OR HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<Set<Users>>(studentAccounts, HttpStatus.OK);

    }

    //------------------- Update a User --------------------------------------------------------

    @RequestMapping(value = "/login/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Users> updateStudentAccount(@PathVariable("id") long id, @RequestBody Users studentAccount) {

        Users currentStudentAccount = accountService.readById(id);

        if (currentStudentAccount==null) {
            return new ResponseEntity<Users>(HttpStatus.NOT_FOUND);
        }
        Users updatedStudentAccount = new Users.Builder()
                .copy(currentStudentAccount)
                .password(studentAccount.getPassword())
                .role(studentAccount.getRole())
                .build();

        accountService.update(updatedStudentAccount);
        return new ResponseEntity<Users>(updatedStudentAccount, HttpStatus.OK);
    }

    //------------------- Delete a User --------------------------------------------------------

    @RequestMapping(value = "/login/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Users> deleteStudentAccount(@PathVariable("id") long id) {
        Users studentAccount = accountService.readById(id);
        if (studentAccount == null) {
            return new ResponseEntity<Users>(HttpStatus.NOT_FOUND);
        }
        accountService.delete(studentAccount);
        return new ResponseEntity<Users>(HttpStatus.NO_CONTENT);
    }
}
