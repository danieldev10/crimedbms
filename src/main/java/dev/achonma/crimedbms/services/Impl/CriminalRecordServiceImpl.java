package dev.achonma.crimedbms.services.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.achonma.crimedbms.models.CriminalRecord;
import dev.achonma.crimedbms.repo.CriminalRecordRepo;
import dev.achonma.crimedbms.services.CriminalRecordService;

@Service
public class CriminalRecordServiceImpl implements CriminalRecordService {
    @Autowired
    private CriminalRecordRepo criminalRecordRepo;

    @Transactional
    @Override
    public List<CriminalRecord> get() {
        return criminalRecordRepo.get();
    }

    @Transactional
    @Override
    public CriminalRecord get(Long id) {
        return criminalRecordRepo.get(id);
    }

    @Transactional
    @Override
    public CriminalRecord save(CriminalRecord criminalRecord) {
        return criminalRecordRepo.save(criminalRecord); // Return the saved CriminalRecord
    }

    @Transactional
    @Override
    public void delete(Long id) {
        criminalRecordRepo.delete(id);
    }

}
