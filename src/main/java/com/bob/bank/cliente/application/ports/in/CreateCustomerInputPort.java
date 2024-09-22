package com.bob.bank.cliente.application.ports.in;

import com.bob.bank.cliente.adapters.in.controller.request.CustomerRequest;

public interface CreateCustomerInputPort {
    void create(CustomerRequest customer);
}
