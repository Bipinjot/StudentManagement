package com.example.demo.result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ResultController {
    @Autowired
    private ResultService resultService;
    @RequestMapping(method = RequestMethod.GET, value="/results")
    public ResponseEntity<List<Result>> getAllResults(){
        try {
            List<Result> result = resultService.getAllResults();
            if (result.size() <= 0)
            {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Result not found");
            }
            return ResponseEntity.ok(result);
        }
        catch (Exception e) {
            // Handle the exception and return an appropriate response
            // You can customize the error message based on the exception type
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new ArrayList<>());
        }
    }

    @RequestMapping(method = RequestMethod.GET, value="/results/user/{userId}")
    public ResponseEntity<List<Result>> getAllResultsByUserId(@PathVariable int userId){
        try {
            List<Result> result = resultService.getAllResultsByUserId(userId);
            if (result.size() <= 0)
            {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Result not found");
            }
            return ResponseEntity.ok(result);
        }
        catch (Exception e) {
            // Handle the exception and return an appropriate response
            // You can customize the error message based on the exception type
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new ArrayList<>());
        }
    }

    @RequestMapping(method = RequestMethod.GET, value="/results/course/{courseId}")
    public ResponseEntity<List<Result>> getAllResultsByCourseId(@PathVariable int courseId){
        try{
            List<Result> result = resultService.getAllResultsByCourseId(courseId);
            if (result.size() <= 0)
            {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Result not found by given course id");
            }
            return ResponseEntity.ok(result);
        }
        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new ArrayList<>());
        }
    }

    @RequestMapping(method = RequestMethod.GET, value="/results/{id}")
    public ResponseEntity<Result> getResult(@PathVariable int id){
        try{
            Result result = resultService.getResult(id);
            if (result == null)
            {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Result not found by given course id");
            }
            return ResponseEntity.ok(result);
        }
        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new Result());
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/results")
    public ResponseEntity<String> addResult(@RequestBody Result user){
        try {
            resultService.addResult(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("Result Added");
        }
        catch (DataIntegrityViolationException e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Result already exists or User/Course doesn't exist");
        }
        catch (JpaObjectRetrievalFailureException e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User or course doesn't exist");
        }
        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error in adding a new result");
        }

    }

    @RequestMapping(method = RequestMethod.PUT, value = "/results/")
    public ResponseEntity<String> updateResult(@RequestBody Result user){
        try {
            resultService.updateResult(user);
            return ResponseEntity.status(HttpStatus.OK).body("Result Saved");
        }
        catch (DataIntegrityViolationException e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Result already exists for the course and the user");
        }
        catch (JpaObjectRetrievalFailureException e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User or course doesn't exist");
        }
        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error in updating a new result");
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/results/{id}")
    public ResponseEntity<String> deleteResult(@PathVariable int id){
        try{
            //Check for getResultId
            ResponseEntity<Result> response= getResult(id);
            HttpStatus status = (HttpStatus) response.getStatusCode();
            if(status == HttpStatus.OK){
                resultService.deleteResult(id);
                return ResponseEntity.status(HttpStatus.OK).body("Result Deleted");
            }
            else{
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No result found");
            }

        }
        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error in deleting result");
        }
    }
}
