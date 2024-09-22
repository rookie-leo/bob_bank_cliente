package com.bob.bank.cliente.adapters.out;

import com.bob.bank.cliente.adapters.out.repositories.CustomerRepository;
import com.bob.bank.cliente.adapters.out.repositories.entity.AddressEntity;
import com.bob.bank.cliente.adapters.out.repositories.entity.CustomerEntity;
import com.bob.bank.cliente.adapters.security.impl.JwtServiceImpl;
import com.bob.bank.cliente.application.core.domain.Address;
import com.bob.bank.cliente.application.core.domain.Customer;
import com.bob.bank.cliente.application.core.domain.CustomerLogin;
import com.bob.bank.cliente.application.core.domain.Telefone;
import com.bob.bank.cliente.application.core.usecase.security.JwtService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerAdapterTest {

    private JwtService jwtService = mock(JwtServiceImpl.class);
    private PasswordEncoder encoder = mock(PasswordEncoder.class);
    private CustomerRepository repository = mock(CustomerRepository.class);
    private AuthenticationManager authenticationManager = mock(AuthenticationManager.class);

    private CustomerAdapter adapter = new CustomerAdapter(jwtService, encoder, repository, authenticationManager);

    @Test
    void should_save_customer_success() {
        Customer customer = getCustomer();

        customer.setTelefones(Arrays.asList(new Telefone(
                "11",
                "12345678"
        )));

        adapter.create(customer);

        ArgumentCaptor<CustomerEntity> customerEntityCaptor = ArgumentCaptor.forClass(CustomerEntity.class);
        verify(repository, times(1)).save(customerEntityCaptor.capture());

        CustomerEntity savedEntity = customerEntityCaptor.getValue();

        assertEquals(customer.getAddress().toEntity(), savedEntity.getAddress());
        assertEquals(1, savedEntity.getTelefones().size());
    }

    @Test
    void should_save_customer_without_telefone_success() {
        Customer customer = getCustomer();

        adapter.create(customer);

        ArgumentCaptor<CustomerEntity> customerEntityCaptor = ArgumentCaptor.forClass(CustomerEntity.class);
        verify(repository, times(1)).save(customerEntityCaptor.capture());

        CustomerEntity savedEntity = customerEntityCaptor.getValue();

        assertEquals(customer.getAddress().toEntity(), savedEntity.getAddress());
        assertEquals(0, savedEntity.getTelefones().size());
    }

    @Test
    void should_not_save_invelid_customer_success() {
        assertThrows(NullPointerException.class, () -> {
                    adapter.create(null);
                }
        );

        verify(repository, never()).save(any());
    }

    @Test
    void should_sigin_valid_customer_success() {
        var customer = createCustomerLogin();
        var entity = getCustomerEntity();

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(any());
        when(repository.findByCpf(customer.getCpf())).thenReturn(Optional.of(entity));
        when(jwtService.generateToken(entity)).thenReturn("FakeToken");

        var token = adapter.login(customer);

        assertNotNull(token);
        verify(authenticationManager, times(1)).authenticate(any());
        verify(repository, times(1)).findByCpf(any());
        verify(jwtService, times(1)).generateToken(any());
    }

    @Test
    void should_not_sigin_invalid_customer() {
        var customer = new CustomerLogin("", "teste");
        String message = "Usuario não cadastrado";

        var ex = assertThrows(UsernameNotFoundException.class, () -> {
            adapter.login(customer);
        });

        assertNotNull(ex);
        assertEquals(message, ex.getMessage());
        verify(authenticationManager, times(1)).authenticate(any());
        verify(repository, times(1)).findByCpf(any());
        verify(jwtService, never()).generateToken(any());
    }

    private static Customer getCustomer() {
        Customer customer = new Customer(
                "Fulano",
                "da Silva",
                "01234567895",
                "senha"
        );

        customer.setAddress(new Address(
                "Jd. Teste",
                "Rua: Teste",
                "1234",
                "São Paulo",
                "SP",
                "0000-000"
        ));

        return customer;
    }

    private CustomerEntity getCustomerEntity() {
        CustomerEntity entity = new CustomerEntity(
                "Fulano",
                "da Silva",
                "01234567895",
                "senha"
        );

        entity.setAddress(new AddressEntity(
                "Jd. Teste",
                "Rua: Teste",
                "1234",
                "São Paulo",
                "SP",
                "0000-000"
        ));

        return entity;
    }

    private CustomerLogin createCustomerLogin() {
        return new CustomerLogin("01234567894", "teste");
    }
}