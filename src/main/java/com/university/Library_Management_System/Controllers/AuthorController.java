package com.university.Library_Management_System.Controllers;

import com.university.Library_Management_System.Models.Author;
import com.university.Library_Management_System.Services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("author")
public class AuthorController {
    @Autowired
    AuthorService authorService;

    @PostMapping("/add")
    public ResponseEntity<?> addAuthor(@RequestBody Author author) {
        try {
            Author author1 = authorService.addAuthor(author);
            return ResponseEntity.status(HttpStatus.CREATED).body(author1);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("message : Failed to add author\n" + "Error : " + e.getMessage());
        }

    }

    @GetMapping("/getAuthor")
    public ResponseEntity<?> getAuthor(@RequestParam int authorId) {
        try {
            Author author = authorService.getAuthor(authorId);
            if(author != null) {
                return ResponseEntity.status(HttpStatus.FOUND).body(author);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("message : Author not found of ID - " + authorId);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("message : Failed to Fetch Author\n" + "Error : " + e.getMessage());
        }
    }

    @GetMapping("/getAllAuthors")
    public ResponseEntity<?> getAllAuthors() {
        try {
            List<Author> authors = authorService.getAllAuthors();
            if(!authors.isEmpty()) {
                return ResponseEntity.status(HttpStatus.FOUND).body(authors);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("message : No Author Found!");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("message : Failed to Fetch Authors\n" + "Error : " + e.getMessage());
        }
    }

    @PatchMapping("/updateAuthor")
    public ResponseEntity<?> updateAuthor(@RequestParam("authorId") int authorId, @RequestBody Author author) {
        try {
            Author author1 = authorService.updateAuthor(authorId, author);
            if(author1 != null) {
                return ResponseEntity.status(HttpStatus.OK).body(author1);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("message : Author is Not Found of ID - " + authorId);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("message : Failed to update Author\n" + "Error : " + e.getMessage());
        }
    }

    @DeleteMapping("/deleteAuthor")
    public ResponseEntity<?> deleteAuthor(@RequestParam("authorId") int authorId) {
        try {
            Author author = authorService.deleteAuthor(authorId);
            if(author != null)
                return ResponseEntity.status(HttpStatus.OK).body(author);
            else return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body("message : Author is Not Found of ID : " + authorId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body("message : Failed to delete Author" + "\nError : " + e.getMessage());
        }
    }
}
