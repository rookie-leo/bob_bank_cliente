package com.bob.bank.cliente.config;

import com.bob.bank.cliente.adapters.out.CustomerAdapter;
import com.bob.bank.cliente.application.core.usecase.CustomerUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerManagerConfig {

    @Bean
    public CustomerUseCase createCustomerUseCase(CustomerAdapter customerAdapter) {
        return new CustomerUseCase(customerAdapter);
    }

}
