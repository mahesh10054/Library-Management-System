package com.example.Library.Management.System.Controller;

import com.example.Library.Management.System.Entities.LibraryCard;
import com.example.Library.Management.System.Service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/card")
public class CardController {
    @Autowired
    CardService cardService;

    @PostMapping("/generatePlainCard")
    public ResponseEntity generatePlainCard()
    {
        LibraryCard newCard = cardService.generatedCard();
        String message = "New Card Generated Successfully And The Card Number Is : "+ newCard.getCardNo();
        return new ResponseEntity(message,HttpStatus.OK);
    }
    @PutMapping("/associateWithStudent")
    public ResponseEntity associateWithStudent(@RequestParam Integer studentId,@RequestParam Integer cardNo)
    {
        String result = cardService.associateStudentWithCard(studentId,cardNo);

        return new ResponseEntity(result,HttpStatus.OK);
    }
}
