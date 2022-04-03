package mk.ukim.finki.emt.model;

import lombok.Data;
import mk.ukim.finki.emt.model.enumerations.BookCategory;

import javax.persistence.*;

@Data
@Entity
public class Book {
//: id (Long), name (String),
//category (enum), author (Author), availableCopies (Integer)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(value = EnumType.STRING)
    BookCategory category;

    @ManyToOne
    private Author author;

    private Integer availableCopies;

    public Book() {
    }

    public Book(String name, BookCategory category, Author author, Integer availableCopies) {
        this.name = name;
        this.category = category;
        this.author = author;
        this.availableCopies = availableCopies;
    }
}
