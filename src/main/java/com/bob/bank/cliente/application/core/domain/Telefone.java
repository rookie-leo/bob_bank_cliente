package com.bob.bank.cliente.application.core.domain;

import com.bob.bank.cliente.adapters.out.repositories.entity.TelefoneEntity;

public class Telefone {

    private String ddd;
    private String numero;

    public Telefone(String ddd, String numero) {
        this.ddd = ddd;
        this.numero = numero;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public TelefoneEntity toEntity() {
        return new TelefoneEntity(
                this.ddd,
                this.numero
        );
    }
}
