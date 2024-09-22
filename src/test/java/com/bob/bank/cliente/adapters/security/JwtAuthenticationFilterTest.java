package com.bob.bank.cliente.adapters.security;

import com.bob.bank.cliente.application.core.usecase.security.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JwtAuthenticationFilterTest {

    @Mock
    private JwtService jwtService;

    @Mock
    private CustomerDetailsServiceImpl customerDetailsService;

    @InjectMocks
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        SecurityContextHolder.clearContext();
    }

    @Test
    void shouldAuthenticateValidJwtToken() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        MockFilterChain filterChain = new MockFilterChain();

        String token = "valid-jwt-token";
        String cpf = "12345678901";
        request.addHeader("Authorization", "Bearer " + token);

        UserDetails userDetails = mock(UserDetails.class);

        when(jwtService.extractUserName(token)).thenReturn(cpf);
        when(customerDetailsService.loadUserByUsername(cpf)).thenReturn(userDetails);
        when(jwtService.isTokenValid(token, userDetails)).thenReturn(true);


        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);


        SecurityContext securityContext = SecurityContextHolder.getContext();
        assertNotNull(securityContext.getAuthentication());
        assertTrue(securityContext.getAuthentication() instanceof UsernamePasswordAuthenticationToken);

        UsernamePasswordAuthenticationToken authToken = (UsernamePasswordAuthenticationToken) securityContext.getAuthentication();
        assertEquals(userDetails, authToken.getPrincipal());
        assertNull(authToken.getCredentials());


        verify(jwtService, times(1)).extractUserName(token);
        verify(customerDetailsService, times(1)).loadUserByUsername(cpf);
        verify(jwtService, times(1)).isTokenValid(token, userDetails);
    }

    @Test
    void shouldNotAuthenticateWhenTokenIsInvalid() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        MockFilterChain filterChain = new MockFilterChain();

        String token = "invalid-jwt-token";
        String cpf = "12345678901";
        request.addHeader("Authorization", "Bearer " + token);

        UserDetails userDetails = mock(UserDetails.class);

        when(jwtService.extractUserName(token)).thenReturn(cpf);
        when(customerDetailsService.loadUserByUsername(cpf)).thenReturn(userDetails);
        when(jwtService.isTokenValid(token, userDetails)).thenReturn(false);


        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);


        SecurityContext securityContext = SecurityContextHolder.getContext();
        assertNull(securityContext.getAuthentication());


        verify(jwtService, times(1)).extractUserName(token);
        verify(customerDetailsService, times(1)).loadUserByUsername(cpf);
        verify(jwtService, times(1)).isTokenValid(token, userDetails);
    }

    @Test
    void shouldNotAuthenticateWhenAuthorizationHeaderIsMissing() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        MockFilterChain filterChain = new MockFilterChain();


        request.addHeader("Authorization", "");


        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);


        SecurityContext securityContext = SecurityContextHolder.getContext();
        assertNull(securityContext.getAuthentication());


        verify(jwtService, never()).extractUserName(anyString());
        verify(customerDetailsService, never()).loadUserByUsername(anyString());
        verify(jwtService, never()).isTokenValid(anyString(), any());
    }

    @Test
    void shouldNotAuthenticateWhenAuthorizationHeaderIsInvalid() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        MockFilterChain filterChain = new MockFilterChain();


        request.addHeader("Authorization", "InvalidToken");


        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);


        SecurityContext securityContext = SecurityContextHolder.getContext();
        assertNull(securityContext.getAuthentication());


        verify(jwtService, never()).extractUserName(anyString());
        verify(customerDetailsService, never()).loadUserByUsername(anyString());
        verify(jwtService, never()).isTokenValid(anyString(), any());
    }
}
