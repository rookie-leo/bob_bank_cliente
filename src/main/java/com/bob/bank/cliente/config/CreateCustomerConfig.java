package com.bob.bank.cliente.config;

import com.bob.bank.cliente.adapters.out.CustomerAdapter;
import com.bob.bank.cliente.application.core.usecase.CreateCustomerUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CreateCustomerConfig {

    @Bean
    public CreateCustomerUseCase createCustomerUseCase(CustomerAdapter customerAdapter) {
        return new CreateCustomerUseCase(customerAdapter);
    }

}
