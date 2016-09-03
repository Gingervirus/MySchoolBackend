package backend.client;

import backend.domain.Subject;
import backend.services.SubjectService;
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
public class SubjectController {
    // Inject Service
    @Autowired
    private SubjectService accountService;

    //-------------------Create a User--------------------------------------------------------

    @RequestMapping(value = "/subject/", method = RequestMethod.POST)
    public ResponseEntity<Subject> createStudentAccount(@RequestBody Subject studentAccount, UriComponentsBuilder ucBuilder) {

        Subject studAccount = accountService.create(studentAccount);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/subject/{id}").buildAndExpand(studentAccount.getSubject_code()).toUri());

        return new ResponseEntity<>(studAccount, HttpStatus.CREATED);

    }

    //-------------------Retrieve Single USer--------------------------------------------------------
    @RequestMapping(value = "/subject/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Subject> getStudentAccount(@PathVariable("id") long id) {

        Subject studentAccount = accountService.readById(id);

        if (studentAccount == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(studentAccount, HttpStatus.OK);

    }


    //-------------------Retrieve All Subject--------------------------------------------------------

    @RequestMapping(value = "/subjects/", method = RequestMethod.GET)

    public ResponseEntity<Set<Subject>> getStudentAccounts() {

        Set<Subject> studentAccounts = accountService.readAll();
        if(studentAccounts.isEmpty()){
            return new ResponseEntity<Set<Subject>>(HttpStatus.NO_CONTENT);// OR HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<Set<Subject>>(studentAccounts, HttpStatus.OK);

    }

    //------------------- Update a User --------------------------------------------------------

    @RequestMapping(value = "/subject/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Subject> updateStudentAccount(@PathVariable("id") long id, @RequestBody Subject studentAccount) {

        Subject currentStudentAccount = accountService.readById(id);

        if (currentStudentAccount==null) {
            return new ResponseEntity<Subject>(HttpStatus.NOT_FOUND);
        }
        Subject updatedStudentAccount = new Subject.Builder()
                .copy(currentStudentAccount)
                .title(studentAccount.getTitle())
                .description(studentAccount.getDescription())
                .prescribed_book(studentAccount.getPrescribed_book())
                .build();

        accountService.update(updatedStudentAccount);
        return new ResponseEntity<Subject>(updatedStudentAccount, HttpStatus.OK);
    }

    //------------------- Delete a User --------------------------------------------------------

    @RequestMapping(value = "/subject/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Subject> deleteStudentAccount(@PathVariable("id") long id) {
        Subject studentAccount = accountService.readById(id);
        if (studentAccount == null) {
            return new ResponseEntity<Subject>(HttpStatus.NOT_FOUND);
        }
        accountService.delete(studentAccount);
        return new ResponseEntity<Subject>(HttpStatus.NO_CONTENT);
    }
}
