package com.openclassrooms.starterjwt.security.services;

import com.openclassrooms.starterjwt.security.jwt.AuthTokenFilter;
import com.openclassrooms.starterjwt.security.jwt.JwtUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class UserDetailsImplTest {



    @Mock
    private JwtUtils jwtUtils;

    @Mock
    private UserDetailsServiceImpl userDetailsService;

    @Mock
    private UserDetailsImpl userDetails;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain filterChain;

    @InjectMocks
    private AuthTokenFilter authTokenFilter;

    @BeforeEach
    void setUp() {
        userDetails = UserDetailsImpl.builder()
                .id(1L)
                .username("totoland")
                .firstName("toto")
                .lastName("tata")
                .admin(false)
                .password("Password")
                .build();
    }

    @Test
    void getAuthorities() {

    }

    @Test
    void isAccountNonExpired() {
        assertTrue(userDetails.isAccountNonExpired());
    }

    @Test
    void isAccountNonLocked() {
        assertTrue(userDetails.isAccountNonLocked());
    }

    @Test
    void isCredentialsNonExpired() {
        assertTrue(userDetails.isCredentialsNonExpired());
    }

    @Test
    void isEnabled() {
        assertTrue(userDetails.isEnabled());
    }

    @Test
    void testEquals() {
        UserDetailsImpl userDetails1 = UserDetailsImpl.builder()
                .id(1L)
                .username("totoland")
                .firstName("toto")
                .lastName("tata")
                .admin(false)
                .password("Password")
                .build();
        UserDetailsImpl userDetails2 = UserDetailsImpl.builder()
                .id(1L)
                .username("totoland")
                .firstName("toto")
                .lastName("tata")
                .admin(false)
                .password("Password")
                .build();
        assertTrue(userDetails1.equals(userDetails2));

    }

    @Test
    void getId() {



    }

    @Test
    void getUsername() {
    }

    @Test
    void getFirstName() {
    }

    @Test
    void getLastName() {
    }

    @Test
    void getAdmin() {

    }

    @Test
    void getPassword() {
    }

    @Test
    void builder() {
    }

    @Test
    void testGetAuthorities() {

    }

    @Test
    void testIsAccountNonExpired() {
    }

    @Test
    void testIsAccountNonLocked() {
    }

    @Test
    void testIsCredentialsNonExpired() {
    }

    @Test
    void testIsEnabled() {
    }

    @Test
    void testEquals1() {
    }

    @Test
    void testGetId() {
    }

    @Test
    void testGetUsername() {
    }

    @Test
    void testGetFirstName() {
    }

    @Test
    void testGetLastName() {
    }

    @Test
    void testGetAdmin() {

    }

    @Test
    void testGetPassword() {
    }

    @Test
    void testBuilder() {

    }
}