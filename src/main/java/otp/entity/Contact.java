package otp.entity;

import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Component
@Table(name = "contact")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    String email;
    String phoneNum;
    String faxNum;
    String mobileNum;
    int addressId;

    public Contact() {
    }

    public Contact(int id, String email, String phoneNum, String faxNum, String mobileNum, int addressId) {
        this.id = id;
        this.email = email;
        this.phoneNum = phoneNum;
        this.faxNum = faxNum;
        this.mobileNum = mobileNum;
        this.addressId = addressId;
    }

    public Contact(String email, String phoneNum, String faxNum, String mobileNum, int addressId) {
        this.email = email;
        this.phoneNum = phoneNum;
        this.faxNum = faxNum;
        this.mobileNum = mobileNum;
        this.addressId = addressId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getFaxNum() {
        return faxNum;
    }

    public void setFaxNum(String faxNum) {
        this.faxNum = faxNum;
    }

    public String getMobileNum() {
        return mobileNum;
    }

    public void setMobileNum(String mobileNum) {
        this.mobileNum = mobileNum;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", faxNum='" + faxNum + '\'' +
                ", mobileNum='" + mobileNum + '\'' +
                ", addressId=" + addressId +
                '}';
    }
}
