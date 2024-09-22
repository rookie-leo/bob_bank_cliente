package com.bob.bank.cliente.adapters.out.repositories.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity(name = "address")
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String neighborhood;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String number;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false, name = "zip")
    private String zip;

    @Deprecated
    public AddressEntity() {}

    public AddressEntity(String neighborhood,
                         String street,
                         String number,
                         String city,
                         String state,
                         String zip) {
        this.neighborhood = neighborhood;
        this.street = street;
        this.number = number;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

    @Override
    public String toString() {
        return "AddressEntity{" +
                "id=" + id +
                ", neighborhood='" + neighborhood + '\'' +
                ", street='" + street + '\'' +
                ", number='" + number + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip='" + zip + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressEntity that = (AddressEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(neighborhood, that.neighborhood) && Objects.equals(street, that.street) && Objects.equals(number, that.number) && Objects.equals(city, that.city) && Objects.equals(state, that.state) && Objects.equals(zip, that.zip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, neighborhood, street, number, city, state, zip);
    }
}
