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
    public ResponseEntity getCard(@RequestParam("cardId") int cardId) {
        try {
            LibraryCard card = cardService.getCard(cardId);

            if(card != null) {
                return ResponseEntity.status(HttpStatus.FOUND).body(card);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("message : Card Not Found of ID - " + cardId);
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
    public ResponseEntity associateCardAndStudent(@RequestParam("cardId") int cardId, @RequestParam("studentId") int studentId) {
        try {
            String res = cardService.associateCardAndStudent(cardId, studentId);
            return ResponseEntity.status(HttpStatus.OK).body(res);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PatchMapping("/updateCard")
    public ResponseEntity updateCard(@RequestParam("cardId") int cardId, @RequestBody LibraryCard card) {
        try {
            LibraryCard card1 = cardService.updateCard(cardId, card);
            if(card1 != null) {
                return ResponseEntity.status(HttpStatus.OK).body(card1);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("message : Card is Not Found of ID - " + cardId);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("message : Failed to update Card\n" + "Error : " + e.getMessage());
        }
    }

    @DeleteMapping("/deleteCard")
    public ResponseEntity deleteCard(@RequestParam("cardId") int cardId) {
        try {
            LibraryCard card = cardService.deleteCard(cardId);
            if(card != null)
                return ResponseEntity.status(HttpStatus.OK).body(card);
            else return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body("message : Card is Not Found of ID : " + cardId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body("message : Failed to delete Card" + "\nError : " + e.getMessage());
        }
    }
}
