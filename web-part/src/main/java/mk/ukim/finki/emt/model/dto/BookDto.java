package mk.ukim.finki.emt.model.dto;

import lombok.Data;
import mk.ukim.finki.emt.model.enumerations.BookCategory;

@Data
public class BookDto {

    private String name;

    BookCategory category;

    private Long author;

    private Integer availableCopies;

    public BookDto() {
    }

    public BookDto(String name, BookCategory category, Long author, Integer availableCopies) {
        this.name = name;
        this.category = category;
        this.author = author;
        this.availableCopies = availableCopies;
    }
}
