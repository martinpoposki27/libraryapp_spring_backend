package mk.ukim.finki.emt.repository;

import mk.ukim.finki.emt.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

    List<Country> findAllByNameLike(String text);

    void deleteByName(String name);
}
