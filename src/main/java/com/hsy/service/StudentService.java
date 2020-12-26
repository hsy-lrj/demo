package com.hsy.service;

import com.hsy.domain.Student;

import java.util.List;

public interface StudentService {

    //添加学生
    void saveStudent(Student student);

    //更新学生
    int updateStudent(String id, Student student);

    //根据id查询学生
    Student findStudentById(String id);

    //查询大于age的学生
    List<Student> findStudentByAge(Integer age);

    List<Student> findAll();
}
