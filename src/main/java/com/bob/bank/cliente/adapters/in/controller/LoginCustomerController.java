package com.bob.bank.cliente.adapters.in.controller;

import com.bob.bank.cliente.adapters.in.controller.request.CustomerLoginRequest;
import com.bob.bank.cliente.application.ports.in.LoginCustomerInputPort;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/customers")
public class LoginCustomerController {

    private final LoginCustomerInputPort customerInputPort;

    @Autowired
    public LoginCustomerController(LoginCustomerInputPort customerInputPort) {
        this.customerInputPort = customerInputPort;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody CustomerLoginRequest loginRequest) {
        String token = customerInputPort.login(loginRequest);

        return ResponseEntity
                .ok()
                .header("token", token)
                .build();
    }


}
