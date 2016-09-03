package backend.client;

import backend.domain.Timetable;
import backend.services.TimetableService;
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
public class TimetableController {
    // Inject Service
    @Autowired
    private TimetableService accountService;

    //-------------------Create a User--------------------------------------------------------

    @RequestMapping(value = "/Timetable/", method = RequestMethod.POST)
    public ResponseEntity<Timetable> createStudentAccount(@RequestBody Timetable studentAccount, UriComponentsBuilder ucBuilder) {

        Timetable studAccount = accountService.create(studentAccount);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/Timetable/{id}").buildAndExpand(studentAccount.getTimetable_id()).toUri());

        return new ResponseEntity<>(studAccount, HttpStatus.CREATED);

    }

    //-------------------Retrieve Single USer--------------------------------------------------------
    @RequestMapping(value = "/Timetable/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Timetable> getStudentAccount(@PathVariable("id") long id) {

        Timetable studentAccount = accountService.readById(id);

        if (studentAccount == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(studentAccount, HttpStatus.OK);

    }


    //-------------------Retrieve All Timetable--------------------------------------------------------

    @RequestMapping(value = "/Timetables/", method = RequestMethod.GET)

    public ResponseEntity<Set<Timetable>> getStudentAccounts() {

        Set<Timetable> studentAccounts = accountService.readAll();
        if(studentAccounts.isEmpty()){
            return new ResponseEntity<Set<Timetable>>(HttpStatus.NO_CONTENT);// OR HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<Set<Timetable>>(studentAccounts, HttpStatus.OK);

    }

    //------------------- Update a User --------------------------------------------------------

    @RequestMapping(value = "/Timetable/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Timetable> updateStudentAccount(@PathVariable("id") long id, @RequestBody Timetable studentAccount) {

        Timetable currentStudentAccount = accountService.readById(id);

        if (currentStudentAccount==null) {
            return new ResponseEntity<Timetable>(HttpStatus.NOT_FOUND);
        }
        Timetable updatedStudentAccount = new Timetable.Builder()
                .copy(currentStudentAccount)
                .period(studentAccount.getPeriod())
                .day(studentAccount.getDay())
                .subject(studentAccount.getSubject())
                .time(studentAccount.getTime())
                .room_nr(studentAccount.getRoom_nr())
                .build();

        accountService.update(updatedStudentAccount);
        return new ResponseEntity<Timetable>(updatedStudentAccount, HttpStatus.OK);
    }

    //------------------- Delete a User --------------------------------------------------------

    @RequestMapping(value = "/Timetable/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Timetable> deleteStudentAccount(@PathVariable("id") long id) {
        Timetable studentAccount = accountService.readById(id);
        if (studentAccount == null) {
            return new ResponseEntity<Timetable>(HttpStatus.NOT_FOUND);
        }
        accountService.delete(studentAccount);
        return new ResponseEntity<Timetable>(HttpStatus.NO_CONTENT);
    }
}
