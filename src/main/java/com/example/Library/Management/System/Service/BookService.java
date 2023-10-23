package com.example.Library.Management.System.Service;

import com.example.Library.Management.System.Entities.Author;
import com.example.Library.Management.System.Entities.Book;
import com.example.Library.Management.System.Enums.Genre;
import com.example.Library.Management.System.Exceptions.AuthorNotFoundException;
import com.example.Library.Management.System.Repository.AuthorRepository;
import com.example.Library.Management.System.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    AuthorRepository authorRepository;

    public String addBook(Book book, Integer authorId) throws Exception
    {
        Optional<Author> optionalAuthor = authorRepository.findById(authorId);
        if(!optionalAuthor.isPresent())
        {
            throw new AuthorNotFoundException("Author Id Enter Not Valid");
        }
        Author author = optionalAuthor.get();
        book.setAuthor(author);

        author.getBookList().add(book);

        //you can only save parent table chiled table auto save
        authorRepository.save(author);

        return "Book Added Successfully";
    }

    public List<String> getBookNameList(Integer authorId)
    {
        List<String> bookName = new ArrayList<>();
        Author author = authorRepository.findById(authorId).get();

        List<Book> bookList = author.getBookList();

        for(Book book : bookList)
        {
            bookName.add(book.getBookName());
        }
        return bookName;
    }
//    public List<String> getBooksByGenre(Genre genre)
//    {
//        List<Book> listBook = bookRepository.getBooksByGenre(genre);
//
//        List<String> listBookName = new ArrayList<>();
//        for(Book book : listBook)
//        {
//            listBookName.add(book.getBookName());
//        }
//        return listBookName;
//    }
}
