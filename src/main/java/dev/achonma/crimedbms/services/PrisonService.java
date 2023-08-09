package dev.achonma.crimedbms.services;

import java.util.List;

import dev.achonma.crimedbms.models.Prison;

public interface PrisonService {
    List<Prison> get();

    public Prison get(Long id);

    public Prison get(String name);

    void save(Prison prison);

    void delete(Long id);
}
