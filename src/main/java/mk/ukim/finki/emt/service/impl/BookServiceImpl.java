package mk.ukim.finki.emt.service.impl;


import mk.ukim.finki.emt.model.Author;
import mk.ukim.finki.emt.model.Book;
import mk.ukim.finki.emt.model.dto.BookDto;
import mk.ukim.finki.emt.model.enumerations.BookCategory;
import mk.ukim.finki.emt.model.events.BookCreatedEvent;
import mk.ukim.finki.emt.model.exceptions.AuthorNotFoundException;
import mk.ukim.finki.emt.model.exceptions.BookNotFoundException;
import mk.ukim.finki.emt.repository.AuthorRepository;
import mk.ukim.finki.emt.repository.BookRepository;
import mk.ukim.finki.emt.repository.UserRepository;
import mk.ukim.finki.emt.repository.views.BooksPerAuthorViewRepository;
import mk.ukim.finki.emt.service.BookService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final BooksPerAuthorViewRepository viewRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    public BookServiceImpl(AuthorRepository authorRepository,
                           BookRepository bookRepository,
                           UserRepository userRepository,
                           BooksPerAuthorViewRepository viewRepository, ApplicationEventPublisher applicationEventPublisher) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.viewRepository = viewRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return this.bookRepository.findById(id);
    }

    @Override
    public Optional<Book> findByName(String name) {
        return this.bookRepository.findByName(name);
    }

    @Override
    @Transactional
    public Optional<Book> save(String name, BookCategory category, Long author, Integer availableCopies) {
        Author authorObj = this.authorRepository.findById(author)
                .orElseThrow(() -> new AuthorNotFoundException(author));

        //this.bookRepository.deleteByName(name);
        Book book = new Book(name, category, authorObj, availableCopies);
        this.bookRepository.save(book);
        //this.refreshMaterializedView();

        this.applicationEventPublisher.publishEvent(new BookCreatedEvent(book));
        return Optional.of(book);
    }

    @Override
    public Optional<Book> save(BookDto bookDto) {
        Author authorObj = this.authorRepository.findById(bookDto.getAuthor())
                .orElseThrow(() -> new AuthorNotFoundException(bookDto.getAuthor()));

        //this.bookRepository.deleteByName(name);
        Book book = new Book(bookDto.getName(), bookDto.getCategory(), authorObj, bookDto.getAvailableCopies());
        this.bookRepository.save(book);
        //this.refreshMaterializedView();

        //this.applicationEventPublisher.publishEvent(new BookCreatedEvent(book));
        return Optional.of(book);
    }

    @Override
    @Transactional
    public Optional<Book> edit(Long id, String name, BookCategory category, Long author, Integer availableCopies) {
        Book book = this.bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        Author authorObj = this.authorRepository.findById(author).orElseThrow(() -> new AuthorNotFoundException(author));

        book.setName(name);
        book.setCategory(category);
        book.setAuthor(authorObj);
        book.setAvailableCopies(availableCopies);

        this.bookRepository.save(book);
        return Optional.of(book);
    }

    @Override
    public Optional<Book> edit(Long id, BookDto bookDto) {
        Book book = this.bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        Author authorObj = this.authorRepository.findById(bookDto.getAuthor()).orElseThrow(() -> new AuthorNotFoundException(bookDto.getAuthor()));

        book.setName(bookDto.getName());
        book.setCategory(bookDto.getCategory());
        book.setAuthor(authorObj);
        book.setAvailableCopies(bookDto.getAvailableCopies());

        this.bookRepository.save(book);
        return Optional.of(book);
    }


    @Override
    public void deleteById(Long id) {
        this.bookRepository.deleteById(id);
    }

    @Override
    public void markAsTaken(Long id) {
        Book book = this.bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        if(book.getAvailableCopies() > 0){
            book.setAvailableCopies(book.getAvailableCopies() - 1);
        }
        this.bookRepository.save(book);
    }

    @Override
    public void refreshMaterializedView() {
        //this.viewRepository.refreshMaterializedView();
    }

    @Override
    public List<BookCategory> findAllCategories() {
        return Arrays.asList(BookCategory.values());
    }


}
