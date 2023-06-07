package com.example.demo.result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ResultService {

    @Autowired
    private ResultRepository resultRepository;
//    private List<User> users= new ArrayList<>(Arrays.asList(
//            new User(1, "Bipin", "Hara", "bh@gmail.com", "1997-05-08"),
//                new User(1, "Harman", "Hara","hh@gmail.com", "1997-05-08"),
//                new User(3, "Omkar", "Tingre","oy@gmail.com", "1997-05-08"),
//                new User(4, "Subham", "Biswajit","sb@gmail.com", "1997-05-08")
//                ));
public List<Result> getAllResults(){
    List<Result> results = new ArrayList<>();
    resultRepository.findAll().forEach(results::add);
    return results;
}
    public List<Result> getAllResultsByUserId(int userId){
        List<Result> results = new ArrayList<>();
        resultRepository.findResultsByUserId(userId).forEach(results::add);
        return results;
    }

    public List<Result> getAllResultsByCourseId(int courseId){
        List<Result> results = new ArrayList<>();
        resultRepository.findResultsByCourseId(courseId).forEach(results::add);
        return results;
    }

    public Result getResult(int id) {
        Optional<Result> result = resultRepository.findById(id);
        return result.orElse(null); // Return the user or null if not found
    }

    public void addResult(Result result){
        resultRepository.save(result);
    }

    public void updateResult(Result result, int id){
        resultRepository.save(result);
    }

    public void deleteResult(int id){
        resultRepository.deleteById(id);
    }
}
