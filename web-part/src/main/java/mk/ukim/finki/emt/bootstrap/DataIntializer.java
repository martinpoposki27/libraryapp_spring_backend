package mk.ukim.finki.emt.bootstrap;

import lombok.Data;
import mk.ukim.finki.emt.model.Author;
import mk.ukim.finki.emt.model.Book;
import mk.ukim.finki.emt.model.Country;
import mk.ukim.finki.emt.model.enumerations.BookCategory;
import mk.ukim.finki.emt.model.exceptions.AuthorNotFoundException;
import mk.ukim.finki.emt.model.exceptions.CountryNotFoundException;
import mk.ukim.finki.emt.service.AuthorService;
import mk.ukim.finki.emt.service.BookService;
import mk.ukim.finki.emt.service.CountryService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Data
public class DataIntializer {
    private final CountryService countryService;
    private final AuthorService authorService;
    private final BookService bookService;

    public DataIntializer(CountryService countryService, AuthorService authorService, BookService bookService) {
        this.countryService = countryService;
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @PostConstruct
    public void init() {
        Country Serbia = countryService.create("Serbia", "Europe");
        Country Macedonia = countryService.create("N.Macedonia", "Europe");
        Country USA = countryService.create("USA", "N.America");
        Country Canada = countryService.create("Canada", "N.America");
        Country UK = countryService.create("UK", "Europe");
        Country Russia = countryService.create("Russia", "Europe");

        Author author1 = authorService.save("Milorad", "Pavich", Serbia.getId())
                .orElseThrow(() -> new CountryNotFoundException(Serbia.getId()));
        Author author2 = authorService.save("Blazhe", "Koneski", Macedonia.getId())
                .orElseThrow(() -> new CountryNotFoundException(Macedonia.getId()));
        Author author3 = authorService.save("Arthur", "Miller", USA.getId())
                .orElseThrow(() -> new CountryNotFoundException(USA.getId()));
        Author author4 = authorService.save("Margaret", "Atwood", Canada.getId())
                .orElseThrow(() -> new CountryNotFoundException(Canada.getId()));
        Author author5 = authorService.save("George", "Orwell", UK.getId())
                .orElseThrow(() -> new CountryNotFoundException(UK.getId()));
        Author author6 = authorService.save("Fyodor", "Dostoevsky", Russia.getId())
                .orElseThrow(() -> new CountryNotFoundException(Russia.getId()));

        Book book1 = bookService.save("Unique Item", BookCategory.DRAMA, author1.getId(), 32)
                .orElseThrow(() -> new AuthorNotFoundException(author1.getId()));
        Book book2 = bookService.save("Poems", BookCategory.CLASSICS, author2.getId(), 3)
                .orElseThrow(() -> new AuthorNotFoundException(author2.getId()));
        Book book3 = bookService.save("Judy", BookCategory.FANTASY, author3.getId(), 50)
                .orElseThrow(() -> new AuthorNotFoundException(author3.getId()));
        Book book4 = bookService.save("Focus", BookCategory.NOVEL, author3.getId(), 323)
                .orElseThrow(() -> new AuthorNotFoundException(author3.getId()));
        Book book5 = bookService.save("Alias Grace", BookCategory.NOVEL, author4.getId(), 22)
                .orElseThrow(() -> new AuthorNotFoundException(author4.getId()));
        Book book6 = bookService.save("1989", BookCategory.FANTASY, author5.getId(), 12)
                .orElseThrow(() -> new AuthorNotFoundException(author5.getId()));
        Book book7 = bookService.save("Farm", BookCategory.DRAMA, author5.getId(), 75)
                .orElseThrow(() -> new AuthorNotFoundException(author5.getId()));
        Book book8 = bookService.save("Crime and punishment", BookCategory.NOVEL, author6.getId(), 9)
                .orElseThrow(() -> new AuthorNotFoundException(author6.getId()));
        Book book9 = bookService.save("Demons", BookCategory.CLASSICS, author6.getId(), 5)
                .orElseThrow(() -> new AuthorNotFoundException(author6.getId()));

    }

}
