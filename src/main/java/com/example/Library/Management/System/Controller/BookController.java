package com.example.Library.Management.System.Controller;

import com.example.Library.Management.System.Entities.Book;
import com.example.Library.Management.System.Service.AuthorService;
import com.example.Library.Management.System.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Book")
public class BookController {
    @Autowired
    BookService bookService;
    @Autowired
    AuthorService authorService;

    @PostMapping("/addBook")
    public ResponseEntity addBook(@RequestBody Book book, @RequestParam Integer authorId)
    {
        try {
            String result = bookService.addBook(book,authorId);
            return new ResponseEntity(result,HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
