package com.openclassrooms.starterjwt.security.jwt;

import com.openclassrooms.starterjwt.security.services.UserDetailsImpl;
import com.openclassrooms.starterjwt.security.services.UserDetailsServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ActiveProfiles;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class AuthTokenFilterTest {


    @Mock
    private JwtUtils jwtUtils;

    @Mock
    private UserDetailsServiceImpl userDetailsService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain filterChain;

    @InjectMocks
    private AuthTokenFilter authTokenFilter;


    @Test
    public void testDoFilterInternal_ValidJwt_SetsAuthentication() throws Exception {
        // Given
        String validJwt = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VybmFtZSIsInVzZXJfaWQiOjEsImV4cCI6MTY3NjUxODc0OX0.Z123456";
        UserDetails userDetails = new UserDetailsImpl(1L, "totoLand", "toto","tata", true ,"password");
        when(request.getHeader("Authorization")).thenReturn("Bearer " + validJwt );
        when(jwtUtils.validateJwtToken(validJwt)).thenReturn(true);
        when(jwtUtils.getUserNameFromJwtToken(validJwt)).thenReturn("username");
        when(userDetailsService.loadUserByUsername("username")).thenReturn(userDetails);
        // When
        authTokenFilter.doFilterInternal(request, response, filterChain);
        verify(filterChain).doFilter(request, response);

    }

    @Test
    public void testDoFilterInternal_InvalidJwt_DoesNotSetAuthentication() throws Exception {
        // Given
        String invalidJwt
                = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VybmFtZSIsInVzZXJfaWQiOjEsImV4cCI6MTY3NjUxODc0OX0.Z123456";
        when(request.getHeader("Authorization")).thenReturn("Bearer " + invalidJwt);
        when(jwtUtils.validateJwtToken(invalidJwt)).thenReturn(false);
        // When
        authTokenFilter.doFilterInternal(request, response, filterChain);
        // Then
        verify(filterChain).doFilter(request, response);
        assertNull(SecurityContextHolder.getContext().getAuthentication());

    }




}