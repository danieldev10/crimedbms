package dev.achonma.crimedbms.repo.Impl;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dev.achonma.crimedbms.models.Crime;
import dev.achonma.crimedbms.repo.CrimeRepo;

@Repository
public class CrimeRepoImpl implements CrimeRepo {
    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Crime> get() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Crime> query = currentSession.createQuery("from Crime", Crime.class);
        List<Crime> list = query.getResultList();
        return list;
    }

    @Override
    public Crime get(Long id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Crime crimeObj = currentSession.get(Crime.class, id);
        return crimeObj;
    }

    @Override
    public Crime get(String name) {
        Session currentSession = entityManager.unwrap(Session.class);
        Crime crimeObj = currentSession.get(Crime.class, name);
        return crimeObj;
    }

    @Override
    public void save(Crime crime) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.merge(crime);
    }

    @Override
    public void delete(Long id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Crime crimeObj = currentSession.get(Crime.class, id);
        currentSession.remove(crimeObj);
    }

}
