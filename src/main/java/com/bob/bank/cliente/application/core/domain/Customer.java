package com.bob.bank.cliente.application.core.domain;

import com.bob.bank.cliente.adapters.out.repositories.entity.CustomerEntity;

import java.util.List;

public class Customer {

    private String nome;
    private String sobreNome;
    private String cpf;
    private String senha;
    private List<Telefone> telefones;
    private Address address;

    public Customer(String nome, String sobreNome, String cpf, String senha) {
        this.nome = nome;
        this.sobreNome = sobreNome;
        this.cpf = cpf;
        this.senha = senha;
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

    public List<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<Telefone> telefones) {
        this.telefones = telefones;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public CustomerEntity toEntity() {
        return new CustomerEntity(
                this.nome,
                this.sobreNome,
                this.cpf,
                this.senha
        );
    }
}
