package com.university.Library_Management_System.Repositories;

import com.university.Library_Management_System.Models.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
}
