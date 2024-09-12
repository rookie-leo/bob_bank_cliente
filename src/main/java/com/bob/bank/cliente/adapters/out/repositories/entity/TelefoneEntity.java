package com.bob.bank.cliente.adapters.out.repositories.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class TelefoneEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    CustomerEntity customerEntity;

    @Column()
    private String ddd;

    @Column()
    private String numero;


    public TelefoneEntity() {}

    public TelefoneEntity(String ddd, String numero) {
        this.ddd = ddd;
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "TelefoneEntity{" +
                "ddd='" + ddd + '\'' +
                ", numero='" + numero + '\'' +
                ", customerEntity=" + customerEntity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TelefoneEntity that = (TelefoneEntity) o;
        return Objects.equals(ddd, that.ddd) && Objects.equals(numero, that.numero) && Objects.equals(customerEntity, that.customerEntity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ddd, numero, customerEntity);
    }
}
