package com.bob.bank.cliente.config;

import com.bob.bank.cliente.adapters.out.CustomerAdapter;
import com.bob.bank.cliente.application.core.usecase.LoginCustomerUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoginCustomerConfig {

    @Bean
    public LoginCustomerUseCase loginCustomer(CustomerAdapter customerAdapter) {
        return new LoginCustomerUseCase(customerAdapter);
    }

}
