package com.bob.bank.cliente.application.core.usecase;

import com.bob.bank.cliente.adapters.in.controller.request.CustomerRequest;
import com.bob.bank.cliente.application.core.domain.Address;
import com.bob.bank.cliente.application.core.domain.Customer;
import com.bob.bank.cliente.application.core.domain.Telefone;
import com.bob.bank.cliente.application.ports.in.CreateCustomerInputPort;
import com.bob.bank.cliente.application.ports.out.CustomerOutPutPort;

import java.util.ArrayList;
import java.util.List;

public class CreateCustomerUseCase implements CreateCustomerInputPort {

    private CustomerOutPutPort customerAdapter;

    public CreateCustomerUseCase(CustomerOutPutPort customerAdapter) {
        this.customerAdapter = customerAdapter;
    }

    @Override
    public void create(CustomerRequest request) {
        Customer customer = request.toDomain();
        List<Telefone> telefones = new ArrayList<>();
        Address address = request.getAddress().toDomain();

        if (request.getTelefones() != null) {
            request.getTelefones().forEach(tel ->
                    telefones.add(tel.toDomain())
            );
        }

        customer.setTelefones(telefones);
        customer.setAddress(address);

        customerAdapter.create(customer);
    }

}
