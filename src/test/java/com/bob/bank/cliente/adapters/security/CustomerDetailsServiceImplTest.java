package com.bob.bank.cliente.adapters.security;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.bob.bank.cliente.adapters.out.repositories.CustomerRepository;
import com.bob.bank.cliente.adapters.out.repositories.entity.CustomerEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class CustomerDetailsServiceImplTest {

    @Mock
    private CustomerRepository repository;

    @InjectMocks
    private CustomerDetailsServiceImpl customerDetailsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldLoadCustomerByCpf() {
        String cpf = "12345678901";
        CustomerEntity customer = new CustomerEntity();
        customer.setCpf(cpf);

        when(repository.findByCpf(cpf)).thenReturn(Optional.of(customer));

        UserDetails userDetails = customerDetailsService.loadUserByUsername(cpf);
        assertNotNull(userDetails);
        assertEquals(cpf, userDetails.getUsername());
        verify(repository, times(1)).findByCpf(cpf);
    }

    @Test
    void shouldThrowExceptionWhenCustomerNotFound() {
        String cpf = "";

        when(repository.findByCpf(cpf)).thenReturn(Optional.empty());

        UsernameNotFoundException exception = assertThrows(UsernameNotFoundException.class, () -> {
            customerDetailsService.loadUserByUsername(cpf);
        });

        assertEquals("Usuario não cadastrado", exception.getMessage());
        verify(repository, times(1)).findByCpf(cpf);
    }

    @Test
    void shouldThrowExceptionWhenRepositoryThrowsException() {
        String cpf = "12345678901";

        when(repository.findByCpf(cpf)).thenThrow(new RuntimeException("Database connection error"));

        assertThrows(RuntimeException.class, () -> {
            customerDetailsService.loadUserByUsername(cpf);
        });
        verify(repository, times(1)).findByCpf(cpf);
    }
}
