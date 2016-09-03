package backend.client;

import backend.domain.Absent;
import backend.services.AbsentService;
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
public class AbsentController {
    // Inject Service
    @Autowired
    private AbsentService absentService;

    //-------------------Create a StudentAccount--------------------------------------------------------

    @RequestMapping(value = "/absent/", method = RequestMethod.POST)
    public ResponseEntity<Absent> createStudentAccount(@RequestBody Absent studentAccount, UriComponentsBuilder ucBuilder) {

        Absent studAccount = absentService.create(studentAccount);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/absent/{id}").buildAndExpand(studentAccount.getAbsent_ID()).toUri());

        return new ResponseEntity<>(studAccount, HttpStatus.CREATED);

    }

    //-------------------Retrieve Single StudentAccount--------------------------------------------------------
    @RequestMapping(value = "/absent/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Absent> getStudentAccount(@PathVariable("id") long id) {

        Absent studentAccount = absentService.readById(id);

        if (studentAccount == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(studentAccount, HttpStatus.OK);

    }


    //-------------------Retrieve All StudentAccounts--------------------------------------------------------

    @RequestMapping(value = "/absents/", method = RequestMethod.GET)

    public ResponseEntity<Set<Absent>> getStudentAccounts() {

        Set<Absent> studentAccounts = absentService.readAll();
        if(studentAccounts.isEmpty()){
            return new ResponseEntity<Set<Absent>>(HttpStatus.NO_CONTENT);// OR HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<Set<Absent>>(studentAccounts, HttpStatus.OK);

    }

    //------------------- Update a StudentAccount --------------------------------------------------------

    @RequestMapping(value = "/absent/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Absent> updateStudentAccount(@PathVariable("id") long id, @RequestBody Absent studentAccount) {

        Absent currentStudentAccount = absentService.readById(id);

        if (currentStudentAccount==null) {
            return new ResponseEntity<Absent>(HttpStatus.NOT_FOUND);
        }
        Absent updatedStudentAccount = new Absent.Builder()
                .copy(currentStudentAccount)
                .absent_ID(studentAccount.getAbsent_ID())
                .student_nr(studentAccount.getStudent_nr())
                .subject(studentAccount.getSubject())
                .date(studentAccount.getDate())
                .build();

        absentService.update(updatedStudentAccount);
        return new ResponseEntity<Absent>(updatedStudentAccount, HttpStatus.OK);
    }

    //------------------- Delete a StudentAccount --------------------------------------------------------

    @RequestMapping(value = "/absent/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Absent> deleteStudentAccount(@PathVariable("id") long id) {
        Absent studentAccount = absentService.readById(id);
        if (studentAccount == null) {
            return new ResponseEntity<Absent>(HttpStatus.NOT_FOUND);
        }
        absentService.delete(studentAccount);
        return new ResponseEntity<Absent>(HttpStatus.NO_CONTENT);
    }
}
