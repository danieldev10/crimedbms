package dev.achonma.crimedbms.services.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.achonma.crimedbms.models.Crime;
import dev.achonma.crimedbms.repo.CrimeRepo;
import dev.achonma.crimedbms.services.CrimeService;

@Service
public class CrimeServiceImpl implements CrimeService {
    @Autowired
    private CrimeRepo crimeRepo;

    @Transactional
    @Override
    public List<Crime> get() {
        return crimeRepo.get();
    }

    @Transactional
    @Override
    public Crime get(Long id) {
        return crimeRepo.get(id);
    }

    @Transactional
    @Override
    public Crime get(String name) {
        return crimeRepo.get(name);
    }

    @Transactional
    @Override
    public void save(Crime crime) {
        crimeRepo.save(crime);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        crimeRepo.delete(id);
    }

}
