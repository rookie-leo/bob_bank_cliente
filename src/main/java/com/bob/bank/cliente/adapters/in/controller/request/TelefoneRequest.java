package com.bob.bank.cliente.adapters.in.controller.request;

import com.bob.bank.cliente.application.core.domain.Telefone;

public class TelefoneRequest {

    private String ddd;
    private String numero;

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

    public Telefone toDomain() {
        return new Telefone(
                this.ddd,
                this.numero
        );
    }
}
