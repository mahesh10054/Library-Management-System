package com.example.Library.Management.System.Repository;

import com.example.Library.Management.System.Entities.Book;
import com.example.Library.Management.System.Entities.LibraryCard;
import com.example.Library.Management.System.Entities.Transaction;
import com.example.Library.Management.System.Enums.TransactionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Integer> {

    Transaction findTransactionByBookAndCardAndTransactionStatus(Book book, LibraryCard card, TransactionStatus transactionStatus);
}
