package com.university.Library_Management_System.Controllers;

import com.university.Library_Management_System.Models.Author;
import com.university.Library_Management_System.Models.Student;
import com.university.Library_Management_System.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("student")
@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("")
    public String home() {
        return studentService.home();
    }



    @PostMapping("/add")
    public ResponseEntity addStudent(@RequestBody Student student) {
        try {
            Student student1 = studentService.addStudent(student);
            return ResponseEntity.status(HttpStatus.CREATED).body(student1);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("message : Failed to Create Student\n" + "Error : " + e.getMessage());
        }
    }

    @GetMapping("/getStudent")
    public ResponseEntity getStudent(@RequestParam("student_id") Integer student_id) {
         try {
            Student student = studentService.getStudentById(student_id);

            if(student != null) {
                return ResponseEntity.status(HttpStatus.FOUND).body(student);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("message : Student Not Found of ID - " + student_id);
            }
         } catch (Exception e) {
             return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to Fetch Student\n" + "Error : " + e.getMessage());
         }
    }

    @GetMapping("/getAllStudents")
    public ResponseEntity getAllStudent() {
        try {
            List<Student> students = studentService.getAllStudents();

            if(!students.isEmpty()) {
                return ResponseEntity.status(HttpStatus.FOUND).body(students);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("message : Not Student Found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to Fetch Students\n" + "Error : " + e.getMessage());
        }
    }


    @PatchMapping("/updateStudent")
    public ResponseEntity updateStudent(@RequestParam int student_id, @RequestBody Student student) {

        try {
            Student student1 = studentService.updateStudent(student_id, student);
            if(student1 != null) {
                return ResponseEntity.status(HttpStatus.OK).body(student1);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("message : Student Not Found of ID - " + student_id);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("message : Failed to update Student\n" + "Error : " + e.getMessage());
        }
    }

    @DeleteMapping("/deleteStudent")
    public ResponseEntity deleteStudent(@RequestParam int student_id) {
        try {
            Student student = studentService.deleteStudent(student_id);
            if(student != null)
                return ResponseEntity.status(HttpStatus.OK).body(student);
            else return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body("message : Student is Not Found of ID : " + student_id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body("message : Failed to delete Student" + "\nError : " + e.getMessage());
        }
    }
}