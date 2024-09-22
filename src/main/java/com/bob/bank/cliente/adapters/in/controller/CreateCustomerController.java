package com.bob.bank.cliente.adapters.in.controller;

import com.bob.bank.cliente.adapters.in.controller.request.CustomerRequest;
import com.bob.bank.cliente.application.ports.in.CreateCustomerInputPort;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customers")
public class CreateCustomerController {

    private CreateCustomerInputPort customerInputPort;

    @Autowired
    public CreateCustomerController(CreateCustomerInputPort customerInputPort) {
        this.customerInputPort = customerInputPort;
    }

    @PostMapping("/register")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void createCustomer(@Valid @RequestBody CustomerRequest customerRequest) {
        customerInputPort.create(customerRequest);
    }
}
