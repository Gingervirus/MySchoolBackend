package backend.client;

import backend.domain.Parent;
import backend.services.ParentService;
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
public class ParrentController {
    // Inject Service
    @Autowired
    private ParentService accountService;

    //-------------------Create a User--------------------------------------------------------

    @RequestMapping(value = "/parent/", method = RequestMethod.POST)
    public ResponseEntity<Parent> createStudentAccount(@RequestBody Parent studentAccount, UriComponentsBuilder ucBuilder) {

        Parent studAccount = accountService.create(studentAccount);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/parent/{id}").buildAndExpand(studentAccount.getParent_ID()).toUri());

        return new ResponseEntity<>(studAccount, HttpStatus.CREATED);

    }

    //-------------------Retrieve Single USer--------------------------------------------------------
    @RequestMapping(value = "/parent/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Parent> getStudentAccount(@PathVariable("id") long id) {

        Parent studentAccount = accountService.readById(id);

        if (studentAccount == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(studentAccount, HttpStatus.OK);

    }


    //-------------------Retrieve All Parent--------------------------------------------------------

    @RequestMapping(value = "/parents/", method = RequestMethod.GET)

    public ResponseEntity<Set<Parent>> getStudentAccounts() {

        Set<Parent> studentAccounts = accountService.readAll();
        if(studentAccounts.isEmpty()){
            return new ResponseEntity<Set<Parent>>(HttpStatus.NO_CONTENT);// OR HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<Set<Parent>>(studentAccounts, HttpStatus.OK);

    }

    //------------------- Update a User --------------------------------------------------------

    @RequestMapping(value = "/parent/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Parent> updateStudentAccount(@PathVariable("id") long id, @RequestBody Parent studentAccount) {

        Parent currentStudentAccount = accountService.readById(id);

        if (currentStudentAccount==null) {
            return new ResponseEntity<Parent>(HttpStatus.NOT_FOUND);
        }
        Parent updatedStudentAccount = new Parent.Builder()
                .copy(currentStudentAccount)
                .student_nr(studentAccount.getStudent_nr())
                .first_name(studentAccount.getFirst_name())
                .surname(studentAccount.getSurname())
                .cellphone_nr(studentAccount.getCellphone_nr())
                .telephone_nr(studentAccount.getTelephone_nr())
                .e_mail(studentAccount.getE_mail())
                .build();

        accountService.update(updatedStudentAccount);
        return new ResponseEntity<Parent>(updatedStudentAccount, HttpStatus.OK);
    }

    //------------------- Delete a User --------------------------------------------------------

    @RequestMapping(value = "/parent/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Parent> deleteStudentAccount(@PathVariable("id") long id) {
        Parent studentAccount = accountService.readById(id);
        if (studentAccount == null) {
            return new ResponseEntity<Parent>(HttpStatus.NOT_FOUND);
        }
        accountService.delete(studentAccount);
        return new ResponseEntity<Parent>(HttpStatus.NO_CONTENT);
    }
}
