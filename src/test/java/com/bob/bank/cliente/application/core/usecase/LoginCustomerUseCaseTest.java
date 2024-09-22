package com.bob.bank.cliente.application.core.usecase;

import com.bob.bank.cliente.adapters.in.controller.request.CustomerLoginRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class LoginCustomerUseCaseTest {

    @Mock
    private LoginCustomerUseCase useCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void should_login_customer_success() {
        CustomerLoginRequest request = createCustomerLoginRequest();
        String fakeToken = "token";

        when(useCase.login(any(CustomerLoginRequest.class))).thenReturn(fakeToken);

        var token = useCase.login(request);

        verify(useCase, times(1)).login(any(CustomerLoginRequest.class));
        assertNotNull(token);
        assertEquals(fakeToken, token);
    }

    private CustomerLoginRequest createCustomerLoginRequest() {
        var request = new CustomerLoginRequest();
        request.setCpf("01234567894");
        request.setSenha("teste");

        return request;
    }

}