package com.university.Library_Management_System.Services;

import com.university.Library_Management_System.Models.Student;
import com.university.Library_Management_System.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public String home() {
        return "Welcome to the University Library Management System";
    }

    //create student and save in db
    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student getStudentById(int student_id) {
       return studentRepository.findById(student_id).orElse(null);
    }

    // get student methods
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // update student
    public Student updateStudent(int student_id, Student student) {
        Student student1 = this.getStudentById(student_id);

        if(student1 != null) {
            if(student.getName() != null) student1.setName(student.getName());
            if(student.getAddress() != null) student1.setAddress(student.getAddress());
            if(student.getEmail() != null) student1.setEmail(student.getEmail());
            if(student.getAge() != null) student1.setAge(student.getAge());
            if(student.getBranch() != null) student1.setBranch(student.getBranch());

            studentRepository.save(student1);
        }

        return this.getStudentById(student_id);
    }

    // delete student
    public Student deleteStudent(int student_id) {
        Student student1 = getStudentById(student_id);
        if(student1 != null)
            studentRepository.delete(student1);
        return student1;
    }
}
