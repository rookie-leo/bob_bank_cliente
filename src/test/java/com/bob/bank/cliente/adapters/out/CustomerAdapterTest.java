package com.bob.bank.cliente.adapters.out;

import com.bob.bank.cliente.adapters.out.repositories.CustomerRepository;
import com.bob.bank.cliente.adapters.out.repositories.entity.CustomerEntity;
import com.bob.bank.cliente.application.core.domain.Address;
import com.bob.bank.cliente.application.core.domain.Customer;
import com.bob.bank.cliente.application.core.domain.Telefone;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class CustomerAdapterTest {

    private CustomerRepository repository = mock(CustomerRepository.class);

    private CustomerAdapter adapter = new CustomerAdapter(repository);

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

}