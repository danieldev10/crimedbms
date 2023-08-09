package dev.achonma.crimedbms.services.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.achonma.crimedbms.models.State;
import dev.achonma.crimedbms.repo.StateRepo;
import dev.achonma.crimedbms.services.StateService;

@Service
public class StateServiceImpl implements StateService {
    @Autowired
    private StateRepo stateRepo;

    @Transactional
    @Override
    public List<State> get() {
        return stateRepo.get();
    }

    @Transactional
    @Override
    public State get(Long id) {
        return stateRepo.get(id);
    }

    @Transactional
    @Override
    public void save(State state) {
        stateRepo.save(state);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        stateRepo.delete(id);
    }

    @Transactional
    @Override
    public State get(String name) {
        return stateRepo.get(name);
    }

}
