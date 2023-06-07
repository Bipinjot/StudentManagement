package com.example.demo.result;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ResultRepository extends CrudRepository <Result, Integer>{
    public List<Result> findResultsByUserId(int userId);
    public List<Result> findResultsByCourseId(int userId);
}
