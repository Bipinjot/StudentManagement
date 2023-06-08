package com.example.demo.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

public List<Course> getAllCourses(){
    List<Course> courses = new ArrayList<>();
    courseRepository.findAll().forEach(courses::add);
    return courses;
}

    public Course getCourse(int id) {
        Optional<Course> course = courseRepository.findById(id);
        return course.orElse(null); // Return the user or null if not found
    }

    public void addCourse(Course course){
        courseRepository.save(course);
    }

    public void updateCourse(Course course){
        courseRepository.save(course);
    }

    public void deleteCourse(int id){
        courseRepository.deleteById(id);
    }
}
