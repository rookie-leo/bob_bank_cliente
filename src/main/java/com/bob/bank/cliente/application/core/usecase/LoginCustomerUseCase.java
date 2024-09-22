package com.bob.bank.cliente.application.core.usecase;

import com.bob.bank.cliente.adapters.in.controller.request.CustomerLoginRequest;
import com.bob.bank.cliente.application.ports.in.LoginCustomerInputPort;
import com.bob.bank.cliente.application.ports.out.CustomerOutPutPort;

public class LoginCustomerUseCase implements LoginCustomerInputPort {

    private CustomerOutPutPort customerAdapter;

    public LoginCustomerUseCase(CustomerOutPutPort customerAdapter) {
        this.customerAdapter = customerAdapter;
    }

    @Override
    public String login(CustomerLoginRequest loginRequest) {
        return customerAdapter.login(loginRequest.toDomain());
    }

}
