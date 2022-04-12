package mk.ukim.finki.emt.service;



import mk.ukim.finki.emt.model.Book;
import mk.ukim.finki.emt.model.dto.BookDto;
import mk.ukim.finki.emt.model.enumerations.BookCategory;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> findAll();

    Optional<Book> findById(Long id);

    Optional<Book> findByName(String name);

    Optional<Book> save(String name, BookCategory category, Long author, Integer availableCopies);

    Optional<Book> save(BookDto bookDto);

    Optional<Book> edit(Long id, String name, BookCategory category, Long author, Integer availableCopies);

    Optional<Book> edit(Long id, BookDto bookDto);

    void deleteById(Long id);

    void markAsTaken(Long id);

    void refreshMaterializedView();

    List<BookCategory> findAllCategories();
}
