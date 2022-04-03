package mk.ukim.finki.emt.service.impl;


import mk.ukim.finki.emt.model.Country;
import mk.ukim.finki.emt.model.exceptions.CountryNotFoundException;
import mk.ukim.finki.emt.repository.CountryRepository;
import mk.ukim.finki.emt.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public Country create(String name, String continent) {
        if (name==null || name.isEmpty()) {
            throw new IllegalArgumentException();
        }
        Country c = new Country(name,continent);
        countryRepository.save(c);
        return c;
    }

    @Override
    public Country update(Long id, String name, String continent) {
        if (name==null || name.isEmpty()) {
            throw new IllegalArgumentException();
        }
        Country c = countryRepository.findById(id).orElseThrow(() -> new CountryNotFoundException(id));
        c.setName(name);
        c.setContinent(continent);
        countryRepository.save(c);
        return c;
    }

    @Override
    public void deleteById(Long id) {
        Country c = countryRepository.findById(id).orElseThrow(() -> new CountryNotFoundException(id));
        countryRepository.delete(c);
    }

    @Override
    public List<Country> listCountries() {
        return countryRepository.findAll();
    }

    @Override
    public List<Country> searchCountries(String searchText) {
        return countryRepository.findAllByNameLike(searchText);
    }

}
