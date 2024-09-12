package com.bob.bank.cliente.adapters.out.repositories;

import com.bob.bank.cliente.adapters.out.repositories.entity.CustomerEntity;
import com.bob.bank.cliente.application.core.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
}
