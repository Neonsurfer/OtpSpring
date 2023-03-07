package otp.dao.impl;

import org.springframework.stereotype.Repository;
import otp.dao.PersonDao;
import otp.entity.Person;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;


@Repository
public class PersonDaoImpl implements PersonDao {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<Person> listPersons() {
        Query query = entityManager.createQuery(SELECT_PERSON.toString());
        return query.getResultList();
    }

    @Override
    public Person savePerson(String name, String idNumber) {
        Person person = new Person(name, idNumber);
        entityManager.persist(person);
        entityManager.getTransaction().commit();
        entityManager.flush();
        return person;
    }

    @Override
    public Person modifyPerson(int id, String name, String idNumber) {
        Person person = entityManager.find(Person.class, id);
        person.setIdNumber(idNumber);
        person.setName(name);
        entityManager.merge(person);
        entityManager.getTransaction().commit();
        entityManager.flush();

        return entityManager.find(Person.class, id);
    }

    @Override
    public void deletePerson(int id) {
        Person p = entityManager.find(Person.class, id);
        entityManager.remove(p);
        entityManager.getTransaction().commit();
        entityManager.flush();
    }

    @Override
    public boolean existsById(int id) {
        return entityManager.find(Person.class, id) != null;
    }

    @Override
    public boolean existsByIdNumber(String idNumber) {
        Query query = entityManager.createQuery(
                SELECT_PERSON.append("AND person.id_number = ").append(idNumber)
                        .toString());
        return query.getResultList().size() > 0;
    }
}
