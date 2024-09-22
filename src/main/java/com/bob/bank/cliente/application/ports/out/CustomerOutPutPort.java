package com.bob.bank.cliente.application.ports.out;

import com.bob.bank.cliente.adapters.out.repositories.entity.CustomerEntity;
import com.bob.bank.cliente.application.core.domain.Customer;
import com.bob.bank.cliente.application.core.domain.CustomerLogin;

public interface CustomerOutPutPort {

    void create(Customer customer);

    String login(CustomerLogin domain);
}
