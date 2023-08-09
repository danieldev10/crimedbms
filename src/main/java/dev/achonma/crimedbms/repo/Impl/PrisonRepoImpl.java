package dev.achonma.crimedbms.repo.Impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dev.achonma.crimedbms.models.Prison;
import dev.achonma.crimedbms.repo.PrisonRepo;
import javax.persistence.EntityManager;

@Repository
public class PrisonRepoImpl implements PrisonRepo {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Prison> get() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Prison> query = currentSession.createQuery("from Prison", Prison.class);
        List<Prison> list = query.getResultList();
        return list;
    }

    @Override
    public Prison get(Long id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Prison prisonObj = currentSession.get(Prison.class, id);
        return prisonObj;
    }

    @Override
    public void save(Prison prison) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.merge(prison);
    }

    @Override
    public void delete(Long id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Prison prisonObj = currentSession.get(Prison.class, id);
        currentSession.remove(prisonObj);
    }

    @Override
    public Prison get(String name) {
        Session currentSession = entityManager.unwrap(Session.class);
        Prison prisonObj = currentSession.get(Prison.class, name);
        return prisonObj;
    }
}
