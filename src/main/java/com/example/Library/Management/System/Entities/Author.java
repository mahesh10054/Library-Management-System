package com.example.Library.Management.System.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Author")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Author {
    @Id //we write for it denote a primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer authorId;
    @Column(nullable = false,unique = true)
    private String name;
    private int age;
    private double rating;

    //parent class
    @OneToMany(mappedBy = "author",cascade = CascadeType.ALL)
    private List<Book> bookList = new ArrayList<>();
}
