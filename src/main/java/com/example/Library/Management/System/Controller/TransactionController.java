package com.example.Library.Management.System.Controller;

import com.example.Library.Management.System.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    @PostMapping("/issueBook/{bookId}/{cardId}")
    private ResponseEntity issueBook(@PathVariable Integer bookId,@PathVariable Integer cardId)
    {
        try{
            String result = transactionService.issueBook(bookId,cardId);
            return new ResponseEntity(result,HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/returnBook/{bookId}/{cardId}")
    private ResponseEntity returnBook(@PathVariable Integer bookId,@PathVariable Integer cardId)
    {
        try{
            String result = transactionService.returnBook(bookId,cardId);
            return new ResponseEntity(result,HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
