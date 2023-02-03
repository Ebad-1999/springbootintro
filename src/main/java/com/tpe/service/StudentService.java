package com.tpe.service;

import com.tpe.domain.ResourceNotFoundException;
import com.tpe.domain.Student;
import com.tpe.exception.ConflictException;
import com.tpe.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public  void saveStudent(Student student) {
       if(studentRepository.existsByEmail(student.getEmail())){
           throw new ConflictException("Student whose email "+student.getEmail()+ "already exist");
       }
       studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();

    }

    public Student getStudentById(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(
            ()-> new ResourceNotFoundException("Student whose id is "+id+"not found"));
        return student;
    }


    public void deleteStudent(Long id) {
        Student student = getStudentById(id);
        studentRepository.delete(student);
    }
}
