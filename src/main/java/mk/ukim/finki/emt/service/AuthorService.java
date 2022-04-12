package mk.ukim.finki.emt.service;


import mk.ukim.finki.emt.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    Optional<Author> findById(Long id);

    List<Author> findAll();

    Optional<Author> save(String name, String surname, Long countryId);

    void deleteById(Long id);
}
