package com.example.demo.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class CourseController {
    @Autowired
    private CourseService courseService;
    @RequestMapping(method = RequestMethod.GET, value="/courses")
    public ResponseEntity<List<Course>> getAllCourses(){
        try {
            List<Course> course = courseService.getAllCourses();
            if (course.size() <= 0)
            {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Course Result not found");
            }
            return ResponseEntity.ok(course);
        }
        catch (Exception e) {
            // Handle the exception and return an appropriate response
            // You can customize the error message based on the exception type
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new ArrayList<>());
        }
    }

    @RequestMapping(method = RequestMethod.GET, value="/courses/{id}")
    public ResponseEntity<Course> getCourse(@PathVariable int id){
        try{
            Course course = courseService.getCourse(id);
            if (course == null)
            {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Course doesn't exist");
            }
            return ResponseEntity.ok(course);
        }
        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new Course());
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/courses")
    public ResponseEntity<String> addCourse(@RequestBody Course user){
        try {
            courseService.addCourse(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("Course Added");
        }
        catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Course already exists");
        }
        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error in adding a new course");
        }

    }

    @RequestMapping(method = RequestMethod.PUT, value = "/courses/")
    public ResponseEntity<String> updateCourse(@RequestBody Course user){

        try {
            courseService.updateCourse(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("Course Saved");
        }
        catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Course already exists");
        }
        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error in updating course");
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/courses/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable int id){
        try{
            ResponseEntity<Course> response= getCourse(id);
            HttpStatus status = (HttpStatus) response.getStatusCode();
            if(status == HttpStatus.OK){
                courseService.deleteCourse(id);
                return ResponseEntity.status(HttpStatus.OK).body("Course Deleted");
            }
            else{
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No course found");
            }
        }
        catch (DataIntegrityViolationException e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Course cannot be deleted as course exists in Result");
        }
        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error in deleting course");
        }
    }
}
