package dev.achonma.crimedbms.repo;

import java.util.List;

import dev.achonma.crimedbms.models.State;

public interface StateRepo {
    List<State> get();

    public State get(Long id);

    public State get(String name);

    void save(State state);

    void delete(Long id);
}
