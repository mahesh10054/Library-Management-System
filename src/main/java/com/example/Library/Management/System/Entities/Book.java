package com.example.Library.Management.System.Entities;

import com.example.Library.Management.System.Enums.Genre;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table//in case if we don't write any name then is give table name as class name
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id //primary key in table
    private Integer bookId;
    private String bookName;
    private int price;
    private int noOfPages;
    private Genre genre;
    @ManyToOne
    @JoinColumn
    private Author author;

}
