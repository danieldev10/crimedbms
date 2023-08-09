package dev.achonma.crimedbms.repo;

import java.util.List;

import dev.achonma.crimedbms.models.CriminalRecord;

public interface CriminalRecordRepo {
    List<CriminalRecord> get();

    public CriminalRecord get(Long id);

    CriminalRecord save(CriminalRecord criminalRecord);

    void delete(Long id);
}
