package com.bob.bank.cliente.adapters.security.impl;

import com.bob.bank.cliente.adapters.out.repositories.entity.CustomerEntity;
import com.bob.bank.cliente.application.core.usecase.security.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtServiceImpl implements JwtService {

    @Value("${app.security.key}")
    private String key;

    @Override
    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers) {
        final Claims claims = extractAllClaims(token);
        return claimsResolvers.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(this.key);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    @Override
    public String generateToken(CustomerEntity userDetails) {
        Map<String, Object> extraClaims = new HashMap<>();

        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getNome())
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plusSeconds(15 * 60)))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public boolean isTokenValid(String token, UserDetails userDetails) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}
