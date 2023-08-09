package dev.achonma.crimedbms.services;

import java.util.List;

import dev.achonma.crimedbms.models.Crime;

public interface CrimeService {
    List<Crime> get();

    public Crime get(Long id);

    public Crime get(String name);

    void save(Crime crime);

    void delete(Long id);
}
