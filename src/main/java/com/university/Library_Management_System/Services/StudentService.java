package com.university.Library_Management_System.Services;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.university.Library_Management_System.Models.Student;
import com.university.Library_Management_System.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public String home() {
        return "Welcome to the University Library Management System";
    }

    //create student and save in db
    public Student addStudent(Student student) {
        Student student1 = studentRepository.findByEmail(student.getEmail());
        if(student1 == null)
            return studentRepository.save(student);
        return null;
    }

    public Student getStudentById(int studentId) {
       return studentRepository.findById(studentId).orElse(null);
    }

    // get student methods
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // update student
    public Student updateStudent(int studentId, Student student) {
        Student student1 = this.getStudentById(studentId);

        if(student1 != null) {
            if(student.getName() != null) student1.setName(student.getName());
            if(student.getAddress() != null) student1.setAddress(student.getAddress());
            if(student.getEmail() != null) student1.setEmail(student.getEmail());
            if(student.getAge() != null) student1.setAge(student.getAge());
            if(student.getBranch() != null) student1.setBranch(student.getBranch());

            studentRepository.save(student1);
        }

        return this.getStudentById(studentId);
    }

    // delete student
    public Student deleteStudent(int studentId) {
        Student student1 = getStudentById(studentId);
        if(student1 != null)
            studentRepository.delete(student1);
        return student1;
    }
}
