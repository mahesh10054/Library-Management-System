package com.example.Library.Management.System.Service;

import com.example.Library.Management.System.Entities.Book;
import com.example.Library.Management.System.Entities.LibraryCard;
import com.example.Library.Management.System.Entities.Transaction;
import com.example.Library.Management.System.Enums.CardStatus;
import com.example.Library.Management.System.Enums.TransactionStatus;
import com.example.Library.Management.System.Exceptions.*;
import com.example.Library.Management.System.Repository.BookRepository;
import com.example.Library.Management.System.Repository.CardRepository;
import com.example.Library.Management.System.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class TransactionService {
    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    CardRepository cardRepository;

    private static final Integer Max_Limit_Of_Books = 3;
    private static final Integer Fine_Amount_Per_Day = 5;

    public String issueBook(Integer bookId,Integer cardId) throws Exception
    {
        //Validation check
        Transaction transaction = new Transaction();
        transaction.setTransactionStatus(TransactionStatus.PENDING);

        //BookId check
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        if(!bookOptional.isPresent())
        {
            throw new BookNotFound("BookId Entered Is Invalid");
        }

        Book book = bookOptional.get();
        if(!book.isAvailable())
        {
            throw new BookNotAvailable("Book Not Available");
        }

        //CardId check
        Optional<LibraryCard> libraryCardOptional =cardRepository.findById(cardId);
        if(!libraryCardOptional.isPresent())
        {
            throw new CardNotFound("CardId Entered Is Invalid");
        }

        //Check Card Status
        LibraryCard libraryCard = libraryCardOptional.get();
        if(!libraryCard.getCardStatus().equals(CardStatus.ACTIVE))
        {
            throw new InvalidCardStatus("Card Status Is Not Active");
        }

        if(libraryCard.getNoOfBooksIssue() == Max_Limit_Of_Books)
        {
            throw new MaximumBookAllReadyIssue(Max_Limit_Of_Books +" is Maximum Book That Can Be Issue");
        }

        transaction.setTransactionStatus(TransactionStatus.ISSUED);

        libraryCard.setNoOfBooksIssue(libraryCard.getNoOfBooksIssue()+1);

        book.setAvailable(false);

        //setting fk
        transaction.setBook(book);
        transaction.setCard(libraryCard);

        //save relevant entities
        book.getTransactionsList().add(transaction);
        libraryCard.getTransactionList().add(transaction);

        //save the parent entity
        transactionRepository.save(transaction);

        return "The Book with BookId "+bookId+" Has Been Issue to CardId With "+cardId;
    }
    public String returnBook(Integer bookId,Integer cardId)
    {
        Book book = bookRepository.findById(bookId).get();
        LibraryCard card = cardRepository.findById(cardId).get();

        Transaction transaction = transactionRepository.findTransactionByBookAndCardAndTransactionStatus(book,card,TransactionStatus.ISSUED);

        Date issueDate = transaction.getCreateOn();

        //it is use to calculate days this is predefined method
        long milliSecond = Math.abs(System.currentTimeMillis() - issueDate.getTime());
        Long days = TimeUnit.DAYS.convert(milliSecond,TimeUnit.MILLISECONDS);

        int fineAmount = 0;

        if(days > 15)
        {
            fineAmount = Math.toIntExact((days-15)*Fine_Amount_Per_Day);
        }

        Transaction newTransaction = new Transaction();

        newTransaction.setTransactionStatus(TransactionStatus.COMPLETED);
        newTransaction.setReturnDate(new Date());
        newTransaction.setFine(fineAmount);

        //set FK
        newTransaction.setBook(book);
        newTransaction.setCard(card);

        book.setAvailable(true);
        card.setNoOfBooksIssue(card.getNoOfBooksIssue() - 1);

        book.getTransactionsList().add(newTransaction);
        card.getTransactionList().add(newTransaction);

        transactionRepository.save(newTransaction);

        return "Book Has Be Return Successfully";
    }
}
