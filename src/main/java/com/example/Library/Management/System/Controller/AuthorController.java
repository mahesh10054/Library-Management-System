package com.example.Library.Management.System.Controller;

import com.example.Library.Management.System.Entities.Author;
import com.example.Library.Management.System.Service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    AuthorService authorService;

    @PostMapping("/addAuthor")
    public ResponseEntity<String> addAuthor(@RequestBody Author author)
    {
        String result = authorService.addAuthor(author);
        return new ResponseEntity<>(result, HttpStatus.FOUND);
    }

    @GetMapping("/getAllAuthorNames")
    public List<String> getAllAuthorNames()
    {
        return authorService.getAllAuthorNames();
    }

    @GetMapping("/getAuthor/{authorId}")
    public ResponseEntity getAuthor(@PathVariable Integer authorId)
    {
        try
        {
            Author author = authorService.getAuthor(authorId);
            return new ResponseEntity(author,HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
