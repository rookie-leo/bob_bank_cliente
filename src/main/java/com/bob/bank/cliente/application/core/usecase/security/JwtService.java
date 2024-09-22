package com.bob.bank.cliente.application.core.usecase.security;

import com.bob.bank.cliente.adapters.out.repositories.entity.CustomerEntity;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    String extractUserName(String token);

    String generateToken(CustomerEntity userDetails);

    boolean isTokenValid(String token, UserDetails userDetails);
}
