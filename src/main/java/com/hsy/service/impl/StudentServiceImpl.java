package com.hsy.service.impl;

import com.hsy.base.MyException;
import com.hsy.base.ResultCode;
import com.hsy.dao.SchoolDao;
import com.hsy.dao.StudentDao;
import com.hsy.domain.School;
import com.hsy.domain.Student;
import com.hsy.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;
    @Autowired
    private SchoolDao schoolDao;


    //添加学生
    @Override
    public void saveStudent(Student student) {
        try {
            studentDao.save(student);
            schoolDao.save(student.getSchool());
        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException(ResultCode.ERROR, "添加失败");
        }
    }

    //更新学生
    @Override
    public int updateStudent(String id, Student student) {
        try {
            //查询到的数据
            Student student1 = studentDao.findStudentById(id);
            School school = student.getSchool();
            //更新student操作
            student1.setName(student.getName());
            student1.setAge(student.getAge());
            student1.setAddress(student.getAddress());
            student1.setHobby(student.getHobby());
            //更新school
            school.setName(student.getSchool().getName());
            school.setAddress(student.getSchool().getAddress());
            school.setId(id);
            //将更新后的school保存到student中
            student1.setSchool(school);
            //进行更新
            studentDao.save(student1);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException(ResultCode.ERROR, "更新失败");
        }
    }

    //根据id查询学生
    @Override
    public Student findStudentById(String id) {
        Student student = studentDao.findStudentById(id);
        if (student == null) {
            return null;
        }
        School school = schoolDao.findSchoolById(student.getId());
        student.setSchool(school);
        return student;
    }

    //查询大于age的学生
    @Override
    public List<Student> findStudentByAge(Integer age) {
        List<Student> studentList = studentDao.findAllByAgeGreaterThan(age);
        return studentList;
    }

    //查询所有学生
    @Override
    public List<Student> findAll() {
        List<Student> studentList = studentDao.findAll();
        return studentList;
    }

    //查询首页数据
    @Cacheable(value = "student", key = "'studentList'")
    @Override
    public List<Student> findHomePage() {
        List<Student> studentList = new ArrayList<>();
        Student student1 = this.findStudentById("1");
        Student student2 = this.findStudentById("2");
        Student student3 = this.findStudentById("3");
        studentList.add(student1);
        studentList.add(student2);
        studentList.add(student3);
        return studentList;
    }


}
