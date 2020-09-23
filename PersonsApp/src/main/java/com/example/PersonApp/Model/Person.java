package com.example.PersonApp.Model;

import javax.persistence.*;

@Entity
@Table(name= "person", schema = "public")
public class Person {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long personid;
    @Column(name = "firstname")
    private String firstName;
    @Column(name = "lastname")
    private String lastName;
    @Column(name = "address")
    private String address;
    @Column(name = "city")
    private String city;


    public Person() {
    }

    public Person(String firstName, String lastName, String address, String city) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address=address;
        this.city=city;
    }

    @Override
    public String toString() {
        return String.format(
                "Persons[id=%d, firstName='%s', lastName='%s', address='%s', city= '%s']",
                personid, firstName, lastName, address, city);
    }


    public Long getPersonid() {
        return personid;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setPersonid(Long personid) {
        this.personid = personid;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }
}