package com.bob.bank.cliente.adapters.security.impl;

import com.bob.bank.cliente.adapters.out.repositories.entity.CustomerEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class JwtServiceImplTest {

    @InjectMocks
    private JwtServiceImpl jwtService;

    @Value("${app.security.key}")
    private String key = "TYRTfLXGlinOjBggLtCewFWucZH2zMAllp1DkopezlM=";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(jwtService, "key", key);
    }

    @Test
    void should_extractUserName() {
        String customerName = "Customer Name";
        String token = jwtService.generateToken(createCustomerEntity(customerName));

        String extractedCustomerName = jwtService.extractUserName(token);

        assertNotNull(extractedCustomerName);
        assertEquals(customerName, extractedCustomerName);
    }

    @Test
    void should_generateToken() {
        CustomerEntity customer = createCustomerEntity("Customer Name");

        String token = jwtService.generateToken(customer);

        assertNotNull(token);
    }

    private CustomerEntity createCustomerEntity(String nome) {
        CustomerEntity customer = new CustomerEntity();
        customer.setNome(nome);
        return customer;
    }
}