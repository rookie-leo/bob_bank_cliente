package com.bob.bank.cliente.application.ports.in;

import com.bob.bank.cliente.adapters.in.controller.request.CustomerLoginRequest;

public interface LoginCustomerInputPort {
    String login(CustomerLoginRequest loginRequest);
}
