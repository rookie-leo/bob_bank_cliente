package com.bob.bank.cliente.application.core.usecase;

import com.bob.bank.cliente.adapters.in.controller.request.AddressRequest;
import com.bob.bank.cliente.adapters.in.controller.request.CustomerRequest;
import com.bob.bank.cliente.adapters.in.controller.request.TelefoneRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class CustomerUseCaseTest {

    @Mock
    private CustomerUseCase useCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void should_create_customer_success() {
        CustomerRequest request = createCustomerRequest();

        useCase.create(request);

        ArgumentCaptor<CustomerRequest> customerCaptor = ArgumentCaptor.forClass(CustomerRequest.class);
        verify(useCase, times(1)).create(customerCaptor.capture());

        CustomerRequest capturedCustomer = customerCaptor.getValue();

        assertNotNull(capturedCustomer);
        assertNotNull(capturedCustomer.getAddress());
        assertNull(capturedCustomer.getTelefones());
    }

    @Test
    void should_create_customer_with_telefones_success() {
        CustomerRequest request = createCustomerRequest();

        TelefoneRequest telefoneRequest = new TelefoneRequest();
        telefoneRequest.setDdd("11");
        telefoneRequest.setNumero("12345678");
        request.setTelefones(Arrays.asList(telefoneRequest));

        useCase.create(request);

        ArgumentCaptor<CustomerRequest> customerCaptor = ArgumentCaptor.forClass(CustomerRequest.class);
        verify(useCase, times(1)).create(customerCaptor.capture());

        CustomerRequest capturedCustomer = customerCaptor.getValue();

        List<TelefoneRequest> capturedTelefones = capturedCustomer.getTelefones();

        assertNotNull(capturedTelefones);
        assertEquals(1, capturedTelefones.size());
        assertNotNull(capturedCustomer);
        assertNotNull(capturedCustomer.getAddress());
        assertNotNull(capturedCustomer.getTelefones());
    }

    private static CustomerRequest createCustomerRequest() {
        CustomerRequest customerRequest = new CustomerRequest();
        customerRequest.setNome("Fulano");
        customerRequest.setSobreNome("Silva");
        customerRequest.setCpf("12345678901");
        customerRequest.setSenha("teste");

        AddressRequest addressRequest = new AddressRequest();
        addressRequest.setNeighborhood("Jd. teste");
        addressRequest.setStreet("Rua: teste");
        addressRequest.setNumber("1234");
        addressRequest.setCity("São Paulo");
        addressRequest.setStreet("SP");
        addressRequest.setZipCode("12345-000");

        customerRequest.setAddress(addressRequest);

        return customerRequest;
    }
}