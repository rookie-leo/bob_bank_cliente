package com.bob.bank.cliente.adapters.in.controller;

import com.bob.bank.cliente.adapters.in.controller.request.CustomerRequest;
import com.bob.bank.cliente.application.ports.in.CustomerInputPort;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerManagerController {

    private CustomerInputPort customerInputPort;

    @Autowired
    public CustomerManagerController(CustomerInputPort customerInputPort) {
        this.customerInputPort = customerInputPort;
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public void createCustomer(@Valid @RequestBody CustomerRequest customerRequest) {
        customerInputPort.create(customerRequest);
    }

}
