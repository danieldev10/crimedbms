package dev.achonma.crimedbms.repo.Impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dev.achonma.crimedbms.models.CriminalRecord;
import dev.achonma.crimedbms.repo.CriminalRecordRepo;
import javax.persistence.EntityManager;

@Repository
public class CriminalRecordRepoImpl implements CriminalRecordRepo {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<CriminalRecord> get() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<CriminalRecord> query = currentSession.createQuery("from CriminalRecord", CriminalRecord.class);
        List<CriminalRecord> list = query.getResultList();
        return list;
    }

    @Override
    public CriminalRecord get(Long id) {
        Session currentSession = entityManager.unwrap(Session.class);
        CriminalRecord criminalRecordObj = currentSession.get(CriminalRecord.class, id);
        return criminalRecordObj;
    }

    @Override
    public CriminalRecord save(CriminalRecord criminalRecord) {
        Session currentSession = entityManager.unwrap(Session.class);
        CriminalRecord savedCriminalRecord = (CriminalRecord) currentSession.merge(criminalRecord);
        return savedCriminalRecord;
    }

    @Override
    public void delete(Long id) {
        Session currentSession = entityManager.unwrap(Session.class);
        CriminalRecord criminalRecordObj = currentSession.get(CriminalRecord.class, id);
        currentSession.remove(criminalRecordObj);
    }

}
