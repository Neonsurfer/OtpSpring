package otp.entity;

import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Component
@Table(name = "personaddress")
public class PersonAddress {
    int personId;
    int addressesId;

    public PersonAddress() {
    }

    public PersonAddress(int personId, int addressesId) {
        this.personId = personId;
        this.addressesId = addressesId;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public int getAddressesId() {
        return addressesId;
    }

    public void setAddressesId(int addressesId) {
        this.addressesId = addressesId;
    }

    @Override
    public String toString() {
        return "PersonAddresses{" +
                "personId=" + personId +
                ", addressesId=" + addressesId +
                '}';
    }
}
