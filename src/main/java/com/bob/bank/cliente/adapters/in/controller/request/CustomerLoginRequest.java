package com.bob.bank.cliente.adapters.in.controller.request;

import com.bob.bank.cliente.application.core.domain.CustomerLogin;
import jakarta.validation.constraints.NotBlank;

public class CustomerLoginRequest {

    @NotBlank
    private String cpf;

    @NotBlank
    private String senha;

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public CustomerLogin toDomain() {
        return new CustomerLogin(
                this.cpf,
                this.senha
        );
    }
}
