package com.example.demo.result;


import com.example.demo.course.Course;
import com.example.demo.user.User;
import jakarta.persistence.*;
@Entity
@Table(name = "result", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "course_id"})})
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    public  Result() {
    }

    public Result(int id, User user, Course course, String grade) {
        this.id = id;
        this.user = user;
        this.course = course;
        this.grade = grade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Column(nullable = false)
    private String grade;
}
