package com.hsy.controller;

import com.hsy.base.result;
import com.hsy.domain.Student;
import com.hsy.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;


    /**
     * 添加学生
     * @param student
     * @return
     */
    @PostMapping("/saveStudent")
    public result saveStudent(@RequestBody Student student){
        studentService.saveStudent(student);
        return result.ok();
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @GetMapping("/findStudentById/{id}")
    public result findStudentById(@PathVariable String id){
        Student studentById = studentService.findStudentById(id);
        if (studentById==null){
            return result.error().data("msg","您的输入有误");
        }
        return result.ok().data("student",studentById);
    }

    /**
     * 查询所有学生
     * @return
     */
    @GetMapping("/findAll")
    public result findAll(){
        List<Student> studentList = studentService.findAll();
        if (studentList.size()==0){
            return result.error().data("msg","查询失败");
        }
        return result.ok().data("studentList",studentList);
    }

    /**
     * 查询年龄大于age的学生
     * @param age
     * @return
     */
    @GetMapping("/findStudentByAge/{age}")
    public result findStudentByAge(@PathVariable Integer age){
        List<Student> studentList = studentService.findStudentByAge(age);
        if (studentList.size()==0){
            return result.error().data("msg","您查询的条件不在范围内");
        }
        return result.ok().data("studentList",studentList);
    }

    /**
     * 修改学生
     * @param student
     * @return
     */
    @PostMapping("/updateStudent/{id}")
    public result updateStudent(@PathVariable String id,@RequestBody Student student){
        int i = studentService.updateStudent(id, student);
        if (i==1){
            return result.ok();
        }else {
            return result.error().data("msg","修改失败");
        }
    }

    /**
     * 查询所有学生
     * @return
     */
    @GetMapping("/findHomePage")
    public result findHomePage(){
        List<Student> studentList = studentService.findHomePage();
        if (studentList.size()==0){
            return result.error().data("msg","查询失败");
        }
        return result.ok().data("studentList",studentList);
    }


}
