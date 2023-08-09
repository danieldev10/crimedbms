package dev.achonma.crimedbms.services;

import java.util.List;

import dev.achonma.crimedbms.models.CriminalRecord;

public interface CriminalRecordService {
    List<CriminalRecord> get();

    public CriminalRecord get(Long id);

    CriminalRecord save(CriminalRecord criminalRecord);

    void delete(Long id);
}
