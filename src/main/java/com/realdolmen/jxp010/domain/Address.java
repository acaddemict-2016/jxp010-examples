package com.realdolmen.jxp010.domain;

import javax.xml.bind.annotation.*;

@XmlType(propOrder = {"street", "number", "postalCode", "city"})
public class Address {
    private String city;
    private String postalCode;
    private String number;
    private String street;

    public Address() {
    }

    public Address(String city, String postalCode, String number, String street) {
        this.city = city;
        this.postalCode = postalCode;
        this.number = number;
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @XmlAttribute(name="postal_code")
    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
