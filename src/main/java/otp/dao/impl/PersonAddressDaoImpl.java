package otp.dao.impl;

import org.springframework.stereotype.Repository;
import otp.dao.PersonAddressDao;
import otp.entity.PersonAddress;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
public class PersonAddressDaoImpl implements PersonAddressDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public int countAddressConnectionByPersonId(int personId) {
        Query query = entityManager.createQuery(COUNT_CONNECTION
                .append("AND person.id = ").append(personId).toString());
        return query.getFirstResult();
    }

    @Override
    public PersonAddress addPersonAddressConnection(int personId, int addressId) {
        PersonAddress personAddress = new PersonAddress(personId, addressId);
        entityManager.persist(personAddress);
        entityManager.getTransaction().commit();
        entityManager.flush();
        return personAddress;
    }

    @Override
    public boolean existsConnection(int personId, int addressId) {
        Query query = entityManager.createQuery(COUNT_CONNECTION
                .append("AND person.id = ").append(personId).append(" \n")
                .append("AND address.id = ").append(addressId).toString());

        return query.getFirstResult() > 0;
    }

    @Override
    public void deleteConnection(int personId, int addressId) {
        Query query = entityManager.createQuery(SELECT_CONNECTION
                .append("AND person.id = ").append(personId).append(" \n")
                .append("AND address.id = ").append(addressId).toString());
        PersonAddress personAddress = (PersonAddress) query.getSingleResult();
        entityManager.remove(personAddress);
        entityManager.getTransaction().commit();
        entityManager.flush();
    }
}
