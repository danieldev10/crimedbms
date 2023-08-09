package dev.achonma.crimedbms.services.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.achonma.crimedbms.models.Prison;
import dev.achonma.crimedbms.repo.PrisonRepo;
import dev.achonma.crimedbms.services.PrisonService;

@Service
public class PrisonServiceImpl implements PrisonService {
    @Autowired
    private PrisonRepo prisonRepo;

    @Transactional
    @Override
    public List<Prison> get() {
        return prisonRepo.get();
    }

    @Transactional
    @Override
    public Prison get(Long id) {
        return prisonRepo.get(id);
    }

    @Transactional
    @Override
    public void save(Prison prison) {
        prisonRepo.save(prison);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        prisonRepo.delete(id);
    }

    @Transactional
    @Override
    public Prison get(String name) {
        return prisonRepo.get(name);
    }

}
