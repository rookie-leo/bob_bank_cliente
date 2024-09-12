package com.bob.bank.cliente.adapters.out;

import com.bob.bank.cliente.adapters.out.repositories.CustomerRepository;
import com.bob.bank.cliente.adapters.out.repositories.entity.AddressEntity;
import com.bob.bank.cliente.adapters.out.repositories.entity.CustomerEntity;
import com.bob.bank.cliente.adapters.out.repositories.entity.TelefoneEntity;
import com.bob.bank.cliente.application.core.domain.Customer;
import com.bob.bank.cliente.application.ports.out.CustomerOutPutPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerAdapter implements CustomerOutPutPort {

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerAdapter(CustomerRepository repository) {
        this.customerRepository = repository;
    }

    @Override
    public void create(Customer customer) {
        CustomerEntity entity = customer.toEntity();
        List<TelefoneEntity> telefones = new ArrayList<>();

        if (customer.getTelefones() != null) {
            customer.getTelefones().forEach(tel ->
                    telefones.add(tel.toEntity())
            );
        }

        entity.setAddress(customer.getAddress().toEntity());
        entity.setTelefones(telefones);

        customerRepository.save(entity);
    }
}
