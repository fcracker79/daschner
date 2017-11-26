package io.mirko.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Embeddable
public class Address implements Serializable {
    @Basic(optional=false)
    @Column(name="street")
    private String street;

    @Basic(optional=false)
    @Column(name="city")
    private String city;

    @Basic(optional=false)
    @Column(name="country")
    @Enumerated(EnumType.STRING)
    private Country country;

    @Size(max=128)
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Size(max=64)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", country=" + country +
                '}';
    }
}
