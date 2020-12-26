package com.hsy;

import com.hsy.dao.StudentDao;
import com.hsy.domain.School;
import com.hsy.domain.Student;
import com.hsy.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
   private StudentService studentService;

    /**
     * 添加学生
     */
    @Test
    void test1() {
        Student student = new Student();
        student.setId("6");
        student.setName("杨幂");
        student.setAge(34);
        student.setAddress("北京市");
        List<String> hobby = new ArrayList<>();
        hobby.add("拍电影");
        hobby.add("唱歌");
        hobby.add("跳舞");
        student.setHobby(hobby);
        School school = new School();
        school.setId(student.getId());
        school.setName("北京电影学院");
        school.setAddress("北京市海淀区西土城路4号");
        student.setSchool(school);
        studentService.saveStudent(student);
    }

    /**
     * 根据id查询学生
     */
    @Test
    void test2() {
        Student student = studentService.findStudentById("002");
        System.out.println(student);
    }

    /**
     * 更新学生
     */
    @Test
    void test3() {
        Student student = new Student();
        student.setName("白龙马");
        student.setAge(32);
        student.setAddress("龙王");
        List<String> hobby = new ArrayList<>();
        hobby.add("跳舞");
        hobby.add("唱歌");
        student.setHobby(hobby);
        School school = new School();
        school.setName("重庆大学");
        school.setAddress("重庆");
        student.setSchool(school);
       studentService.updateStudent("003",student);
    }

    /**
     * 查询大于age的学生
     */
    @Test
    void test4() {
        List<Student> studentList = studentService.findStudentByAge(23);
        System.out.println(studentList);
    }

}
