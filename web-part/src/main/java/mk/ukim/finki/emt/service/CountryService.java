package mk.ukim.finki.emt.service;


import mk.ukim.finki.emt.model.Country;

import java.util.List;

public interface CountryService {

    Country create(String name, String continent);

    Country update(Long id, String name, String continent);

    void deleteById(Long id);

    List<Country> listCountries();

    List<Country> searchCountries(String searchText);

}
