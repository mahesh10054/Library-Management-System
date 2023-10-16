package com.example.Library.Management.System.Entities;

import com.example.Library.Management.System.Enums.CardStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "LibraryCard")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LibraryCard {
    @Id//primary key of library table
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cardNo;
    @Enumerated(value = EnumType.STRING)
    private CardStatus cardStatus;

    private String nameOnCard;
    //join the two table
    @OneToOne
    @JoinColumn(name = "studentId") //we are connect to parent class by using mobile Number colum
    private Student student; //this will be acts as a foreign key of the library table
}
