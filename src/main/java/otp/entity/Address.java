package otp.entity;

import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Component
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    String city;
    String state;
    String country;
    String addressLine;
    Boolean isPermanent;

    public Address() {
    }

    public Address(int id, String city, String state, String country, String address, Boolean isPermanent) {
        this.id = id;
        this.city = city;
        this.state = state;
        this.country = country;
        this.addressLine = address;
        this.isPermanent = isPermanent;
    }

    public Address(String city, String state, String country, String address, Boolean isPermanent) {
        this.city = city;
        this.state = state;
        this.country = country;
        this.addressLine = address;
        this.isPermanent = isPermanent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddressLine() {
        return addressLine;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }

    public Boolean getPermanent() {
        return isPermanent;
    }

    public void setPermanent(Boolean permanent) {
        isPermanent = permanent;
    }

    @Override
    public String toString() {
        return "Addresses{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", address=Line'" + addressLine + '\'' +
                '}';
    }
}
