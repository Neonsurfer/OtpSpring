package otp.dao.impl;

import org.springframework.stereotype.Repository;
import otp.dao.AddressDao;
import otp.entity.Address;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class AddressDaoImpl implements AddressDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Address> listAddresses() {
        Query query = entityManager.createQuery(SELECT_ADDRESS.toString());
        return query.getResultList();
    }

    @Override
    public Address saveAddress(String city, String state, String country, String addressLine, Boolean isPermanent) {
        Address address = new Address(city, state, country, addressLine, isPermanent);
        entityManager.persist(address);
        entityManager.getTransaction().commit();
        entityManager.flush();
        return address;
    }

    @Override
    public Address modifyAddress(int id, String city, String state, String country, String addressLine, Boolean isPermanent) {
        Address address = entityManager.find(Address.class, id);
        address.setCity(city);
        address.setState(state);
        address.setCountry(country);
        address.setAddressLine(addressLine);
        address.setPermanent(isPermanent);

        entityManager.merge(address);
        entityManager.getTransaction().commit();
        entityManager.flush();
        return entityManager.find(Address.class, id);
    }

    @Override
    public void deleteAddress(int id) {
        Address address = entityManager.find(Address.class, id);
        entityManager.remove(address);
        entityManager.getTransaction().commit();
        entityManager.flush();
    }

    @Override
    public boolean existsById(int id) {
        return entityManager.find(Address.class, id) != null;
    }
}
