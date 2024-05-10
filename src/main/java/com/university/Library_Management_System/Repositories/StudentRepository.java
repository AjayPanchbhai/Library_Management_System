package com.university.Library_Management_System.Repositories;

import com.university.Library_Management_System.Models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    public Student findByEmail(String email);
}
