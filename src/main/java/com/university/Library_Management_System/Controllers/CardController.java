package com.university.Library_Management_System.Controllers;

import com.university.Library_Management_System.Models.Book;
import com.university.Library_Management_System.Models.LibraryCard;
import com.university.Library_Management_System.Services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("card")
public class CardController {
    @Autowired
    private CardService cardService;

    @PostMapping("/add")
    public ResponseEntity addCard() {
        LibraryCard card = cardService.addCard();
        if(card != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(card);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("message : Failed to create Card");
        }
    }

    @GetMapping("/getCard")
    public ResponseEntity getCard(@RequestParam("card_id") int card_id) {
        try {
            LibraryCard card = cardService.getCard(card_id);

            if(card != null) {
                return ResponseEntity.status(HttpStatus.FOUND).body(card);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("message : Card Not Found of ID - " + card_id);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("message : Failed to Fetch Card\n" + "Error : " + e.getMessage());
        }
    }

    @GetMapping("/getAllCards")
    public ResponseEntity getAllCards() {
        try {
            List<LibraryCard> cards = cardService.getAllCards();
            if(!cards.isEmpty()) {
                return ResponseEntity.status(HttpStatus.FOUND).body(cards);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("message : No Card Found!");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("message : Failed to Fetch Card\n" + "Error : " + e.getMessage());
        }
    }

    @PutMapping("/associateCardAndStudent")
    public ResponseEntity associateCardAndStudent(@RequestParam("card_id") int card_id, @RequestParam("student_id") int student_id) {
        try {
            String res = cardService.associateCardAndStudent(card_id, student_id);
            return ResponseEntity.status(HttpStatus.OK).body(res);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PatchMapping("/updateCard")
    public ResponseEntity updateCard(@RequestParam("card_id") int card_id, @RequestBody LibraryCard card) {
        try {
            LibraryCard card1 = cardService.updateCard(card_id, card);
            if(card1 != null) {
                return ResponseEntity.status(HttpStatus.OK).body(card1);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("message : Card is Not Found of ID - " + card_id);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("message : Failed to update Card\n" + "Error : " + e.getMessage());
        }
    }

    @DeleteMapping("/deleteCard")
    public ResponseEntity deleteCard(@RequestParam("card_id") int card_id) {
        try {
            LibraryCard card = cardService.deleteCard(card_id);
            if(card != null)
                return ResponseEntity.status(HttpStatus.OK).body(card);
            else return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body("message : Card is Not Found of ID : " + card_id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body("message : Failed to delete Card" + "\nError : " + e.getMessage());
        }
    }
}
