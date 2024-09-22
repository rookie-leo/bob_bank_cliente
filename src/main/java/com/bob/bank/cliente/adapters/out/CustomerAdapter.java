package com.bob.bank.cliente.adapters.out;

import com.bob.bank.cliente.adapters.out.repositories.CustomerRepository;
import com.bob.bank.cliente.adapters.out.repositories.entity.CustomerEntity;
import com.bob.bank.cliente.adapters.out.repositories.entity.TelefoneEntity;
import com.bob.bank.cliente.application.core.usecase.security.JwtService;
import com.bob.bank.cliente.application.core.domain.Customer;
import com.bob.bank.cliente.application.core.domain.CustomerLogin;
import com.bob.bank.cliente.application.ports.out.CustomerOutPutPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerAdapter implements CustomerOutPutPort {

    private final JwtService jwtService;
    private final PasswordEncoder encoder;
    private final CustomerRepository customerRepository;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public CustomerAdapter(JwtService jwtService,
                           PasswordEncoder encoder,
                           CustomerRepository repository,
                           AuthenticationManager authenticationManager) {
        this.encoder = encoder;
        this.jwtService = jwtService;
        this.customerRepository = repository;
        this.authenticationManager = authenticationManager;
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
        entity.setSenha(encoder.encode(entity.getSenha()));

        customerRepository.save(entity);
    }

    @Override
    public String login(CustomerLogin customer) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        customer.getCpf(),
                        customer.getSenha())
        );

        CustomerEntity customerEntity = customerRepository.findByCpf(customer.getCpf()).orElseThrow(() ->
                new UsernameNotFoundException("Usuario não cadastrado"));

        return jwtService.generateToken(customerEntity);
    }
}
