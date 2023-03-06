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

    public Address() {
    }

    public Address(int id, String city, String state, String country, String address) {
        this.id = id;
        this.city = city;
        this.state = state;
        this.country = country;
        this.addressLine = address;
    }

    public Address(String city, String state, String country, String address) {
        this.city = city;
        this.state = state;
        this.country = country;
        this.addressLine = address;
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
