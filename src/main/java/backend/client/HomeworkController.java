package backend.client;

import backend.domain.Homework;
import backend.services.HomeworkService;
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
public class HomeworkController {
    // Inject Service
    @Autowired
    private HomeworkService homeworkService;

    //-------------------Create a StudentAccount--------------------------------------------------------

    @RequestMapping(value = "/homework/", method = RequestMethod.POST)
    public ResponseEntity<Homework> createStudentAccount(@RequestBody Homework studentAccount, UriComponentsBuilder ucBuilder) {

        Homework studAccount = homeworkService.create(studentAccount);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/homework/{id}").buildAndExpand(studentAccount.getHomework_ID()).toUri());

        return new ResponseEntity<>(studAccount, HttpStatus.CREATED);

    }

    //-------------------Retrieve Single StudentAccount--------------------------------------------------------
    @RequestMapping(value = "/homework/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Homework> getStudentAccount(@PathVariable("id") long id) {

        Homework studentAccount = homeworkService.readById(id);

        if (studentAccount == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(studentAccount, HttpStatus.OK);

    }


    //-------------------Retrieve All StudentAccounts--------------------------------------------------------

    @RequestMapping(value = "/homeworks/", method = RequestMethod.GET)

    public ResponseEntity<Set<Homework>> getStudentAccounts() {

        Set<Homework> studentAccounts = homeworkService.readAll();
        if(studentAccounts.isEmpty()){
            return new ResponseEntity<Set<Homework>>(HttpStatus.NO_CONTENT);// OR HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<Set<Homework>>(studentAccounts, HttpStatus.OK);

    }

    //------------------- Update a StudentAccount --------------------------------------------------------

    @RequestMapping(value = "/homework/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Homework> updateStudentAccount(@PathVariable("id") long id, @RequestBody Homework studentAccount) {

        Homework currentStudentAccount = homeworkService.readById(id);

        if (currentStudentAccount==null) {
            return new ResponseEntity<Homework>(HttpStatus.NOT_FOUND);
        }
        Homework updatedStudentAccount = new Homework.Builder()
                .copy(currentStudentAccount)
                .homework_ID(studentAccount.getHomework_ID())
                .subject(studentAccount.getSubject())
                .description(studentAccount.getDescription())
                .due_date(studentAccount.getDue_date())
                .build();

        homeworkService.update(updatedStudentAccount);
        return new ResponseEntity<Homework>(updatedStudentAccount, HttpStatus.OK);
    }

    //------------------- Delete a StudentAccount --------------------------------------------------------

    @RequestMapping(value = "/homework/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Homework> deleteStudentAccount(@PathVariable("id") long id) {
        Homework studentAccount = homeworkService.readById(id);
        if (studentAccount == null) {
            return new ResponseEntity<Homework>(HttpStatus.NOT_FOUND);
        }
        homeworkService.delete(studentAccount);
        return new ResponseEntity<Homework>(HttpStatus.NO_CONTENT);
    }
}
