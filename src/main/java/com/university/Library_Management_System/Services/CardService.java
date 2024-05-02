package com.university.Library_Management_System.Services;

import com.university.Library_Management_System.Enum.CardStatus;
import com.university.Library_Management_System.Models.Book;
import com.university.Library_Management_System.Models.LibraryCard;
import com.university.Library_Management_System.Models.Student;
import com.university.Library_Management_System.Repositories.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService {
    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private StudentService studentService;

    public LibraryCard addCard() {
        return cardRepository.save(new LibraryCard());
    }

    public LibraryCard getCard(int card_id) {
        return cardRepository.findById(card_id).orElse(null);
    }

    // get all Cards
    public List<LibraryCard> getAllCards() {
        return cardRepository.findAll();
    }

    public String associateCardAndStudent(int card_id, int student_id) throws Exception{
        LibraryCard card = this.getCard(card_id);
        Student student = studentService.getStudentById(student_id);

        if(card == null) {
            throw new Exception("message : Card is Not Found of ID - " + card_id);
        }

        if(student == null) {
            throw new Exception("message : Student is Not Found of ID - " + student_id);
        }

        card.setStudent(student);
        card.setCard_status(CardStatus.ACTIVE);

        cardRepository.save(card);
        return "Card " + card_id + " associate with student " + student_id + " successfully";
    }

    public LibraryCard updateCard(int card_id, LibraryCard card) {
        LibraryCard card1 = this.getCard(card_id);

        if(card1 != null) {
            if(card.getNo_of_books_issue() != null) card1.setNo_of_books_issue(card.getNo_of_books_issue());
            if(card.getCard_status() != null)  card1.setCard_status(card.getCard_status());
            if(card.getStudent() != null) {
                card1.setStudent(card.getStudent());
            }

            cardRepository.save(card1);
        }

        return this.getCard(card_id);
    }

    public LibraryCard deleteCard(int card_id) {
        LibraryCard card = this.getCard(card_id);

        if(card != null)
            cardRepository.deleteById(card_id);

        return card;
    }
}
