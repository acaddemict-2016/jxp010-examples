package com.realdolmen.jxp010.domain;

import com.realdolmen.jxp010.domain.Address;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@XmlRootElement
@XmlType(propOrder = {"firstName", "lastName", "address", "emails"})
public class Person {
    private String firstName;
    private String lastName;
    private Address address;
    private List<String> emails = new ArrayList<>();

    public Person() {
    }

    public Person(String firstName, String lastName, Address address, String... emails) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.emails.addAll(Arrays.asList(emails));
    }

    @XmlElement(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @XmlElement(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @XmlElement(name="email")
    @XmlElementWrapper(name = "emails")
    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }
}
