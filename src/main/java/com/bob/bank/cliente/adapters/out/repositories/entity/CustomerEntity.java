package com.bob.bank.cliente.adapters.out.repositories.entity;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Entity(name = "clientes")
public class CustomerEntity implements UserDetails {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, name = "sobre_nome")
    private String sobreNome;

    @Column(nullable = false, unique = true, name = "cpf")
    private String cpf;

    @Column(nullable = false)
    private String senha;

    @OneToMany(mappedBy = "customerEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TelefoneEntity> telefones;

    @OneToOne(cascade = CascadeType.ALL)
    private AddressEntity address;

    @Deprecated
    public CustomerEntity() {
    }

    public CustomerEntity(String nome,
                          String sobreNome,
                          String cpf,
                          String senha) {
        this.nome = nome;
        this.sobreNome = sobreNome;
        this.cpf = cpf;
        this.senha = senha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<TelefoneEntity> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<TelefoneEntity> telefones) {
        this.telefones = telefones;
    }

    public AddressEntity getAddress() {
        return address;
    }

    public void setAddress(AddressEntity address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerEntity entity = (CustomerEntity) o;
        return Objects.equals(id, entity.id) && Objects.equals(nome, entity.nome) && Objects.equals(sobreNome, entity.sobreNome) && Objects.equals(cpf, entity.cpf) && Objects.equals(senha, entity.senha) && Objects.equals(telefones, entity.telefones) && Objects.equals(address, entity.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, sobreNome, cpf, senha, telefones, address);
    }

    @Override
    public String toString() {
        return "CustomerEntity{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", sobreNome='" + sobreNome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", senha='" + senha + '\'' +
                ", telefones=" + telefones +
                ", address=" + address +
                '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        if (isCredentialsNonExpired()) {
            return this.senha;
        }

        throw new RuntimeException("Refaça seu lgoin"); //TODO - implementar uma exceção personalizada
    }

    @Override
    public String getUsername() {
        return this.cpf;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }
}
