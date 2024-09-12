package com.bob.bank.cliente.adapters.in.controller.request;

import com.bob.bank.cliente.application.core.domain.Address;
import jakarta.validation.constraints.NotNull;

public class AddressRequest {

    @NotNull
    private String neighborhood;

    @NotNull
    private String street;

    @NotNull
    private String number;

    @NotNull
    private String city;

    @NotNull
    private String state;

    @NotNull
    private String zipCode;

    public AddressRequest() {}

    public Address toDomain() {
        return new Address(neighborhood, street, number, city, state, zipCode);
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
