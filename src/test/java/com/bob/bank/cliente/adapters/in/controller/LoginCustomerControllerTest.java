package com.bob.bank.cliente.adapters.in.controller;

import com.bob.bank.cliente.adapters.in.controller.request.CustomerLoginRequest;
import com.bob.bank.cliente.adapters.security.CustomerDetailsServiceImpl;
import com.bob.bank.cliente.adapters.security.impl.JwtServiceImpl;
import com.bob.bank.cliente.application.core.usecase.security.JwtService;
import com.bob.bank.cliente.application.ports.in.LoginCustomerInputPort;
import com.bob.bank.cliente.config.security.TestSecurityConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@Import({JwtServiceImpl.class, TestSecurityConfig.class})
@WebMvcTest(LoginCustomerController.class)
class LoginCustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private CustomerDetailsServiceImpl customerDetailsService;

    @MockBean
    private LoginCustomerInputPort customerInputPort;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void should_signin_customer_success() throws Exception {
       var request = createCustomerLoginRequest();
       String fakeToken = "FakeToke";
       
       when(customerInputPort.login(request)).thenReturn(fakeToken);
       
       mockMvc.perform(post("/api/v1/customers/login")
               .contentType(APPLICATION_JSON)
               .content(objectMapper.writeValueAsString(request)))
               .andExpect(status().isOk());
       
       verify(customerInputPort, times(1)).login(any());
    }

    @Test
    void should_return_badRequest_signin_invalid_customer() throws Exception {
        var request = new CustomerLoginRequest();
        request.setCpf("01234567894");
        request.setSenha("");

        mockMvc.perform(post("/api/v1/customers/login")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());

        verify(customerInputPort, never()).login(any());
    }

    private CustomerLoginRequest createCustomerLoginRequest() {
        var request = new CustomerLoginRequest();
        request.setCpf("01234567894");
        request.setSenha("teste");

        return request;
    }
}