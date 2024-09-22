package com.bob.bank.cliente.adapters.security;

import com.bob.bank.cliente.adapters.out.repositories.CustomerRepository;
import com.bob.bank.cliente.adapters.out.repositories.entity.CustomerEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerDetailsServiceImpl implements UserDetailsService {

    private final CustomerRepository repository;

    public CustomerDetailsServiceImpl(CustomerRepository customerRepository) {
        this.repository = customerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String cpf) throws UsernameNotFoundException {
        CustomerEntity customer = repository.findByCpf(cpf).orElseThrow(() ->
                new UsernameNotFoundException("Usuario não cadastrado"));

        return customer;
    }
}
