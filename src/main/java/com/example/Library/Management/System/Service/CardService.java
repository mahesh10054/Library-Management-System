package com.example.Library.Management.System.Service;

import com.example.Library.Management.System.Entities.LibraryCard;
import com.example.Library.Management.System.Entities.Student;
import com.example.Library.Management.System.Enums.CardStatus;
import com.example.Library.Management.System.Repository.CardRepository;
import com.example.Library.Management.System.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CardService {
    @Autowired
    CardRepository cardRepository;
    @Autowired
    StudentRepository studentRepository;

    public LibraryCard generatedCard()
    {
        LibraryCard card = new LibraryCard();

        card.setCardStatus(CardStatus.NEW);
        card = cardRepository.save(card);

        return card;
    }
    public String associateStudentWithCard(Integer studentId,Integer carNo)
    {
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        Student student = optionalStudent.get();

        Optional<LibraryCard> optionalLibraryCard = cardRepository.findById(carNo);
        LibraryCard libraryCard = optionalLibraryCard.get();

        //Setting the attribute of libraryCard Entity
        libraryCard.setCardStatus(CardStatus.ACTIVE);
        libraryCard.setNameOnCard(student.getName());
        libraryCard.setStudent(student);

        //Setting the attribute of student Entity
        student.setLibraryCard(libraryCard);

        studentRepository.save(student);

        return "Card with "+carNo+" has been associated to student with "+studentId;
    }
}
