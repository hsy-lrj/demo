package com.hsy.dao;

import com.hsy.domain.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface StudentDao extends MongoRepository<Student, String> {
    List<Student> findAllByAgeGreaterThan(Integer age);

    Student findStudentById(String id);

}
