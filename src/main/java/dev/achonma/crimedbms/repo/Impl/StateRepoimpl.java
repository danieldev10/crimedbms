package dev.achonma.crimedbms.repo.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.Session;
import org.hibernate.query.Query;

import dev.achonma.crimedbms.models.State;
import dev.achonma.crimedbms.repo.StateRepo;
import javax.persistence.EntityManager;

@Repository
public class StateRepoimpl implements StateRepo {
    @Autowired
    private EntityManager entityManager;

    @Override
    public List<State> get() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<State> query = currentSession.createQuery("from State", State.class);
        List<State> list = query.getResultList();
        return list;
    }

    @Override
    public State get(Long id) {
        Session currentSession = entityManager.unwrap(Session.class);
        State stateObj = currentSession.get(State.class, id);
        return stateObj;
    }

    @Override
    public void save(State state) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.merge(state);
    }

    @Override
    public void delete(Long id) {
        Session currentSession = entityManager.unwrap(Session.class);
        State stateObj = currentSession.get(State.class, id);
        currentSession.remove(stateObj);
    }

    @Override
    public State get(String name) {
        Session currentSession = entityManager.unwrap(Session.class);
        State stateObj = currentSession.get(State.class, name);
        return stateObj;
    }

}
