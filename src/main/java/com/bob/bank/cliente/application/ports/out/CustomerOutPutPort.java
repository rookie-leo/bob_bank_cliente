package com.bob.bank.cliente.application.ports.out;

import com.bob.bank.cliente.application.core.domain.Customer;

public interface CustomerOutPutPort {

    void create(Customer customer);

}
