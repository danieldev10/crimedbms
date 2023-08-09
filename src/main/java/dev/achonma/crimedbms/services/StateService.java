package dev.achonma.crimedbms.services;

import java.util.List;

import dev.achonma.crimedbms.models.State;

public interface StateService {
    List<State> get();

    public State get(Long id);

    public State get(String name);

    void save(State state);

    void delete(Long id);
}
