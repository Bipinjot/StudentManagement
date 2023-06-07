package com.example.demo.result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public List<Result> getAllResultsByUserId(@PathVariable int userId){
        return resultService.getAllResultsByUserId(userId);
    }

    @RequestMapping("/results/course/{courseId}")
    public List<Result> getAllResultsByCourseId(@PathVariable int courseId){
        return resultService.getAllResultsByCourseId(courseId);
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
