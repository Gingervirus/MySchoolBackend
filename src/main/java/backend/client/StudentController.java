package backend.client;

import backend.domain.Student;
import backend.services.StudentService;
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
public class StudentController {
    // Inject Service
    @Autowired
    private StudentService studentService;

    //-------------------Create a User--------------------------------------------------------

    @RequestMapping(value = "/student/", method = RequestMethod.POST)
    public ResponseEntity<Student> createStudentAccount(@RequestBody Student studentAccount, UriComponentsBuilder ucBuilder) {

        Student studAccount = studentService.create(studentAccount);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/student/{id}").buildAndExpand(studentAccount.getStudent_id()).toUri());

        return new ResponseEntity<>(studAccount, HttpStatus.CREATED);

    }

    //-------------------Retrieve Single USer--------------------------------------------------------
    @RequestMapping(value = "/student/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Student> getStudentAccount(@PathVariable("id") long id) {

        Student studentAccount = studentService.readById(id);

        if (studentAccount == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(studentAccount, HttpStatus.OK);

    }


    //-------------------Retrieve All Student--------------------------------------------------------

    @RequestMapping(value = "/students/", method = RequestMethod.GET)

    public ResponseEntity<Set<Student>> getStudentAccounts() {

        Set<Student> studentAccounts = studentService.readAll();
        if(studentAccounts.isEmpty()){
            return new ResponseEntity<Set<Student>>(HttpStatus.NO_CONTENT);// OR HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<Set<Student>>(studentAccounts, HttpStatus.OK);

    }

    //------------------- Update a User --------------------------------------------------------

    @RequestMapping(value = "/student/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Student> updateStudentAccount(@PathVariable("id") long id, @RequestBody Student studentAccount) {

        Student currentStudentAccount = studentService.readById(id);

        if (currentStudentAccount==null) {
            return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
        }
        Student updatedStudentAccount = new Student.Builder()
                .copy(currentStudentAccount)
                .first_name(studentAccount.getFirst_name())
                .surname(studentAccount.getSurname())
                .cellphone_nr(studentAccount.getCellphone_nr())
                .class_nr(studentAccount.getClass_nr())
                .e_mail(studentAccount.getE_mail())
                .build();

        studentService.update(updatedStudentAccount);
        return new ResponseEntity<Student>(updatedStudentAccount, HttpStatus.OK);
    }

    //------------------- Delete a User --------------------------------------------------------

    @RequestMapping(value = "/student/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Student> deleteStudentAccount(@PathVariable("id") long id) {
        Student studentAccount = studentService.readById(id);
        if (studentAccount == null) {
            return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
        }
        studentService.delete(studentAccount);
        return new ResponseEntity<Student>(HttpStatus.NO_CONTENT);
    }
}
