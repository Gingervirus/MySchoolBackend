package backend.client;

import backend.domain.Detention;
import backend.services.DetentionService;
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
public class DetentionController {
    // Inject Service
    @Autowired
    private DetentionService detentionService;

    //-------------------Create a StudentAccount--------------------------------------------------------

    @RequestMapping(value = "/detention/", method = RequestMethod.POST)
    public ResponseEntity<Detention> createStudentAccount(@RequestBody Detention studentAccount, UriComponentsBuilder ucBuilder) {

        Detention studAccount = detentionService.create(studentAccount);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/detention/{id}").buildAndExpand(studentAccount.getDetention_ID()).toUri());

        return new ResponseEntity<>(studAccount, HttpStatus.CREATED);

    }

    //-------------------Retrieve Single StudentAccount--------------------------------------------------------
    @RequestMapping(value = "/detention/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Detention> getStudentAccount(@PathVariable("id") long id) {

        Detention studentAccount = detentionService.readById(id);

        if (studentAccount == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(studentAccount, HttpStatus.OK);

    }


    //-------------------Retrieve All StudentAccounts--------------------------------------------------------

    @RequestMapping(value = "/detentions/", method = RequestMethod.GET)

    public ResponseEntity<Set<Detention>> getStudentAccounts() {

        Set<Detention> studentAccounts = detentionService.readAll();
        if(studentAccounts.isEmpty()){
            return new ResponseEntity<Set<Detention>>(HttpStatus.NO_CONTENT);// OR HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<Set<Detention>>(studentAccounts, HttpStatus.OK);

    }

    //------------------- Update a StudentAccount --------------------------------------------------------

    @RequestMapping(value = "/detention/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Detention> updateStudentAccount(@PathVariable("id") long id, @RequestBody Detention studentAccount) {

        Detention currentStudentAccount = detentionService.readById(id);

        if (currentStudentAccount==null) {
            return new ResponseEntity<Detention>(HttpStatus.NOT_FOUND);
        }
        Detention updatedStudentAccount = new Detention.Builder()
                .copy(currentStudentAccount)
                .detention_ID(studentAccount.getDetention_ID())
                .studentNr(studentAccount.getStudentNr())
                .date(studentAccount.getDate())
                .reason(studentAccount.getReason())
                .amount_hours(studentAccount.getAmount_hours())
                .build();

        detentionService.update(updatedStudentAccount);
        return new ResponseEntity<Detention>(updatedStudentAccount, HttpStatus.OK);
    }

    //------------------- Delete a StudentAccount --------------------------------------------------------

    @RequestMapping(value = "/detention/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Detention> deleteStudentAccount(@PathVariable("id") long id) {
        Detention studentAccount = detentionService.readById(id);
        if (studentAccount == null) {
            return new ResponseEntity<Detention>(HttpStatus.NOT_FOUND);
        }
        detentionService.delete(studentAccount);
        return new ResponseEntity<Detention>(HttpStatus.NO_CONTENT);
    }
}
