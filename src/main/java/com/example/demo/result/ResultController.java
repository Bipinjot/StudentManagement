package com.example.demo.result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ResultController {
    @Autowired
    private ResultService resultService;
    @RequestMapping("/results")
    public List<Result> getAllResults(){
        return resultService.getAllResults();
    }

    @RequestMapping("/results/user/{userId}")
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

    @RequestMapping("/results/course/{courseId}")
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

    @RequestMapping("/results/{id}")
    public Result getResult(@PathVariable int id){
        return resultService.getResult(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/results")
    public void addResult(@RequestBody Result user){
        resultService.addResult(user);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/results/{id}")
    public void updateResult(@RequestBody Result user, @PathVariable int id){
        resultService.updateResult(user, id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/results/{id}")
    public void deleteResult(@PathVariable int id){
        resultService.deleteResult(id);
    }
}
