package backend.client;

import backend.domain.Teacher;
import backend.services.TeacherService;
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
public class TeacherController {
    // Inject Service
    @Autowired
    private TeacherService accountService;

    //-------------------Create a User--------------------------------------------------------

    @RequestMapping(value = "/teacher/", method = RequestMethod.POST)
    public ResponseEntity<Teacher> createStudentAccount(@RequestBody Teacher studentAccount, UriComponentsBuilder ucBuilder) {

        Teacher studAccount = accountService.create(studentAccount);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/teacher/{id}").buildAndExpand(studentAccount.getTeacher_id()).toUri());

        return new ResponseEntity<>(studAccount, HttpStatus.CREATED);

    }

    //-------------------Retrieve Single USer--------------------------------------------------------
    @RequestMapping(value = "/teacher/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Teacher> getStudentAccount(@PathVariable("id") long id) {

        Teacher studentAccount = accountService.readById(id);

        if (studentAccount == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(studentAccount, HttpStatus.OK);

    }


    //-------------------Retrieve All Teacher--------------------------------------------------------

    @RequestMapping(value = "/teachers/", method = RequestMethod.GET)

    public ResponseEntity<Set<Teacher>> getStudentAccounts() {

        Set<Teacher> studentAccounts = accountService.readAll();
        if(studentAccounts.isEmpty()){
            return new ResponseEntity<Set<Teacher>>(HttpStatus.NO_CONTENT);// OR HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<Set<Teacher>>(studentAccounts, HttpStatus.OK);

    }

    //------------------- Update a User --------------------------------------------------------

    @RequestMapping(value = "/teacher/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Teacher> updateStudentAccount(@PathVariable("id") long id, @RequestBody Teacher studentAccount) {

        Teacher currentStudentAccount = accountService.readById(id);

        if (currentStudentAccount==null) {
            return new ResponseEntity<Teacher>(HttpStatus.NOT_FOUND);
        }
        Teacher updatedStudentAccount = new Teacher.Builder()
                .copy(currentStudentAccount)
                .name(studentAccount.getName())
                .lastName(studentAccount.getLastName())
                .dob(studentAccount.getDob())
                .gender(studentAccount.getGender())
                .e_mail(studentAccount.getE_mail())
                .cell(studentAccount.getCell())
                .build();

        accountService.update(updatedStudentAccount);
        return new ResponseEntity<Teacher>(updatedStudentAccount, HttpStatus.OK);
    }

    //------------------- Delete a User --------------------------------------------------------

    @RequestMapping(value = "/teacher/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Teacher> deleteStudentAccount(@PathVariable("id") long id) {
        Teacher studentAccount = accountService.readById(id);
        if (studentAccount == null) {
            return new ResponseEntity<Teacher>(HttpStatus.NOT_FOUND);
        }
        accountService.delete(studentAccount);
        return new ResponseEntity<Teacher>(HttpStatus.NO_CONTENT);
    }
}
