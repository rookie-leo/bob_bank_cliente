package com.bob.bank.cliente.adapters.in.controller.request;

import com.bob.bank.cliente.application.core.domain.Customer;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class CustomerRequest {

    @NotBlank
    private String nome;

    @NotBlank
    private String sobreNome;

    @NotBlank
    private String cpf;

    @NotBlank
    private String senha;

    private List<TelefoneRequest> telefones;

    @NotNull
    private AddressRequest address;

    public CustomerRequest() {}

    public Customer toDomain() {
        return new Customer(nome, sobreNome, cpf, senha);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobreNome() {
        return sobreNome;
    }

    public void setSobreNome(String sobreNome) {
        this.sobreNome = sobreNome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<TelefoneRequest> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<TelefoneRequest> telefones) {
        this.telefones = telefones;
    }

    public AddressRequest getAddress() {
        return address;
    }

    public void setAddress(AddressRequest address) {
        this.address = address;
    }
}
