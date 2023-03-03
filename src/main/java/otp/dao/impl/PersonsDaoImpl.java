package otp.dao.impl;

import org.springframework.stereotype.Repository;
import otp.dao.PersonsDao;
import otp.entity.Persons;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class PersonsDaoImpl implements PersonsDao {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<Persons> listPersons() {
        Query query = entityManager.createQuery(SELECT_PERSONS.toString());
        return query.getResultList();
    }
}
