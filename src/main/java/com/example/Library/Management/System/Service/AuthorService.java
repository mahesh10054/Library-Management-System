package com.example.Library.Management.System.Service;

import com.example.Library.Management.System.Entities.Author;
import com.example.Library.Management.System.Repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;
    public String addAuthor(Author author) {
        authorRepository.save(author);
        return "Author Added Successfully On DB";
    }

    public List<String> getAllAuthorNames() {
        List<Author> authors = authorRepository.findAll();

        List<String> result = new ArrayList<>();
        for(Author author : authors)
        {
            result.add(author.getName());
        }
        return result;
    }

    public Author getAuthor(Integer authorId) throws Exception{
        Optional<Author> optionalAuthor = authorRepository.findById(authorId);

        if(!optionalAuthor.isPresent())
        {
            throw new Exception("The id Entered is Invalid");
        }

        return optionalAuthor.get();
    }
}
