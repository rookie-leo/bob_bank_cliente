package com.bob.bank.cliente.adapters.in.controller;

import com.bob.bank.cliente.adapters.in.controller.request.AddressRequest;
import com.bob.bank.cliente.adapters.in.controller.request.CustomerRequest;
import com.bob.bank.cliente.adapters.in.controller.request.TelefoneRequest;
import com.bob.bank.cliente.application.ports.in.CustomerInputPort;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerManagerController.class)
public class CustomerManagerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerInputPort customerInputPort;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createCustomer_ShouldReturnCreatedStatus() throws Exception {
        CustomerRequest customerRequest = createCustomerRequest();

        doNothing().when(customerInputPort).create(any(CustomerRequest.class));

        mockMvc.perform(post("/api/v1/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerRequest)))
                .andExpect(status().isCreated());

        verify(customerInputPort, times(1)).create(any());
    }

    @Test
    void createCustomer_whit_telefone_ShouldReturnCreatedStatus() throws Exception {
        CustomerRequest customerRequest = createCustomerRequestWithTelefone();

        doNothing().when(customerInputPort).create(any(CustomerRequest.class));

        mockMvc.perform(post("/api/v1/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerRequest)))
                .andExpect(status().isCreated());

        verify(customerInputPort, times(1)).create(any(CustomerRequest.class));
    }

    @Test
    void createCustomer_ShouldReturnBadRequest_WhenInvalidRequest() throws Exception {
        CustomerRequest invalidRequest = new CustomerRequest();
        invalidRequest.setNome("Fulano");
        invalidRequest.setSobreNome("Silva");

        mockMvc.perform(post("/api/v1/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidRequest)))
                .andExpect(status().isBadRequest());

        verify(customerInputPort, never()).create(any());
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

    private static CustomerRequest createCustomerRequestWithTelefone() {
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

        TelefoneRequest telefoneRequest = new TelefoneRequest();
        telefoneRequest.setDdd("11");
        telefoneRequest.setNumero("12345678");

        customerRequest.setAddress(addressRequest);
        customerRequest.setTelefones(Arrays.asList(telefoneRequest));

        return customerRequest;
    }
}
