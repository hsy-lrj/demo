package com.hsy.service.impl;

import com.hsy.base.MyException;
import com.hsy.base.ResultCode;
import com.hsy.dao.SchoolDao;
import com.hsy.dao.StudentDao;
import com.hsy.domain.School;
import com.hsy.domain.Student;
import com.hsy.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
        try{
            studentDao.save(student);
            schoolDao.save(student.getSchool());
        }catch (Exception e){
            e.printStackTrace();
            throw new MyException(ResultCode.ERROR,"添加失败");
        }
    }

    //更新学生
    @Override
    public int updateStudent(String id, Student student) {
        try{
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
            //将更新后的school保存到student中
            student1.setSchool(school);
            //进行更新
            studentDao.save(student1);
            return 1;
        }catch (Exception e){
            e.printStackTrace();
            throw new MyException(ResultCode.ERROR,"更新失败");
        }
    }

    //根据id查询学生
    @Override
    public Student findStudentById(String id) {
       try{
           Student student = studentDao.findStudentById(id);
           School school = schoolDao.findSchoolBy(student.getId());
           student.setSchool(school);
           return student;
       }catch (Exception e){
           e.printStackTrace();
           throw new MyException(ResultCode.ERROR,"您的操作有误");
       }
    }

    //查询大于age的学生
    @Override
    public List<Student> findStudentByAge(Integer age) {
        try{
            List<Student> studentList = studentDao.findAllByAgeGreaterThan(age);
            return studentList;
        }catch (Exception e){
            e.printStackTrace();
            throw new MyException(ResultCode.ERROR,"您的操作有误");
        }

    }

    //查询所有学生
    @Override
    public List<Student> findAll() {
        List<Student> studentList = studentDao.findAll();
        return studentList;
    }


}