package com.tpe.controller;

import com.tpe.domain.Student;
import com.tpe.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController // @Controller is inside of @RestController
//@Controller

@RequestMapping("/students") // http:localhost:8081/students
public class StudentController {


    /*
    {
    "firstName" :"Mustafa",
    "lastName" :"k",
    "phoneNumber" :"5254445891",
    "email" :"mk12@gmail.com",
    "grade" :"100"
}
     */
    @Autowired
    private StudentService studentService;

    @PostMapping  //http://localhost:8080/students + Post
    public ResponseEntity<Map<String, String>> saveStudent(@Valid @RequestBody Student student){
        //@Valid --> validates the fields of Student object
        //@RequestBody --> maps the  json data to Student obj
        //ResponseEntity --> for return type and http status code

        studentService.saveStudent(student);
        Map<String, String> map = new HashMap<>();
        map.put("message", "Student is created successfully");
        map.put("status", "true");
        return new ResponseEntity<>(map, HttpStatus.CREATED);

    }

    @GetMapping() //http://localhost:8080/students + Get
    public ResponseEntity<List<Student>> getAllStudent(){
        List<Student> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);

    }

    @GetMapping("/query") //http://localhost:8080/students/query?id=1 + Get
    public ResponseEntity <Student> getStudentById(@Valid @RequestParam("id") Long id){
        Student student = studentService.getStudentById(id);
        return ResponseEntity.ok(student);
    }

    @GetMapping("{id}") //http://localhost:8080/students/2 + Get
    public ResponseEntity<Student> getStudentByIdUsingPathVariable(@PathVariable ("id") Long id){
        Student student = studentService.getStudentById(id);
        return ResponseEntity.ok(student);
    }

    @DeleteMapping("/{id}") //http://localhost:8080/students
    public ResponseEntity<Map<String, String>> deleteStudentById(@PathVariable("id") Long id){
        studentService.deleteStudent(id);
        Map<String, String> map = new HashMap<>();
        map.put("message", "Student is deleted successfully");
        map.put("status", "true");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }



}