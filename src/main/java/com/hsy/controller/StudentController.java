package com.hsy.controller;

import com.hsy.base.MyException;
import com.hsy.base.result;
import com.hsy.domain.Student;
import com.hsy.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private RedisTemplate redisTemplate;

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
        return result.ok().data("student",studentById);
    }

    /**
     * 查询所有学生
     * @return
     */
    @GetMapping("/findAll")
    public result findAll(){
        List<Student> studentList = studentService.findAll();
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


}
