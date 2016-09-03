package backend.client;

import backend.domain.Mark;
import backend.services.MarkService;
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
public class MarkController {
    // Inject Service
    @Autowired
    private MarkService accountService;

    //-------------------Create a User--------------------------------------------------------

    @RequestMapping(value = "/mark/", method = RequestMethod.POST)
    public ResponseEntity<Mark> createStudentAccount(@RequestBody Mark studentAccount, UriComponentsBuilder ucBuilder) {

        Mark studAccount = accountService.create(studentAccount);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/mark/{id}").buildAndExpand(studentAccount.getMark_ID()).toUri());

        return new ResponseEntity<>(studAccount, HttpStatus.CREATED);

    }

    //-------------------Retrieve Single USer--------------------------------------------------------
    @RequestMapping(value = "/mark/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Mark> getStudentAccount(@PathVariable("id") long id) {

        Mark studentAccount = accountService.readById(id);

        if (studentAccount == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(studentAccount, HttpStatus.OK);

    }


    //-------------------Retrieve All Mark--------------------------------------------------------

    @RequestMapping(value = "/marks/", method = RequestMethod.GET)

    public ResponseEntity<Set<Mark>> getStudentAccounts() {

        Set<Mark> studentAccounts = accountService.readAll();
        if(studentAccounts.isEmpty()){
            return new ResponseEntity<Set<Mark>>(HttpStatus.NO_CONTENT);// OR HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<Set<Mark>>(studentAccounts, HttpStatus.OK);

    }

    //------------------- Update a User --------------------------------------------------------

    @RequestMapping(value = "/mark/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Mark> updateStudentAccount(@PathVariable("id") long id, @RequestBody Mark studentAccount) {

        Mark currentStudentAccount = accountService.readById(id);

        if (currentStudentAccount==null) {
            return new ResponseEntity<Mark>(HttpStatus.NOT_FOUND);
        }
        Mark updatedStudentAccount = new Mark.Builder()
                .copy(currentStudentAccount)
                .title(studentAccount.getTitle())
                .date(studentAccount.getDate())
                .mark(studentAccount.getMark())
                .build();

        accountService.update(updatedStudentAccount);
        return new ResponseEntity<Mark>(updatedStudentAccount, HttpStatus.OK);
    }

    //------------------- Delete a User --------------------------------------------------------

    @RequestMapping(value = "/mark/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Mark> deleteStudentAccount(@PathVariable("id") long id) {
        Mark studentAccount = accountService.readById(id);
        if (studentAccount == null) {
            return new ResponseEntity<Mark>(HttpStatus.NOT_FOUND);
        }
        accountService.delete(studentAccount);
        return new ResponseEntity<Mark>(HttpStatus.NO_CONTENT);
    }
}
