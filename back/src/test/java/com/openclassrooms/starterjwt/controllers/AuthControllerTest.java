package com.openclassrooms.starterjwt.controllers;

import com.openclassrooms.starterjwt.models.User;
import com.openclassrooms.starterjwt.payload.request.LoginRequest;
import com.openclassrooms.starterjwt.payload.request.SignupRequest;
import com.openclassrooms.starterjwt.payload.response.JwtResponse;
import com.openclassrooms.starterjwt.payload.response.MessageResponse;
import com.openclassrooms.starterjwt.repository.UserRepository;
import com.openclassrooms.starterjwt.security.jwt.JwtUtils;
import com.openclassrooms.starterjwt.security.services.UserDetailsImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class AuthControllerTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtUtils jwtUtils;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AuthController authController;

    @Test
    void shouldAuthenticateUserAndReturnJwtResponse() {
        Long id = 1L;
        boolean isAdmin = false;

        UserDetailsImpl userDetails = UserDetailsImpl
                .builder()
                .username("toto.com")
                .firstName("toto")
                .lastName("tata")
                .id(id)
                .password("password")
                .build();

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null);

        when(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken("toto.com", "password")))
                .thenReturn(authentication);
        when(jwtUtils.generateJwtToken(authentication)).thenReturn("jwt");
        when(userRepository.findByEmail("toto.com")).thenReturn(
                Optional.of(User
                        .builder()
                        .id(id)
                        .email("toto.com")
                        .password("password")
                        .firstName("toto")
                        .lastName("tata")
                        .admin(isAdmin)
                        .build()));

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("toto.com");
        loginRequest.setPassword("password");

        AuthController authController = new AuthController(authenticationManager, passwordEncoder, jwtUtils,
                userRepository);
        ResponseEntity<?> response = authController.authenticateUser(loginRequest);
        JwtResponse responseBody = (JwtResponse) response.getBody();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("toto.com", responseBody.getUsername());
        assertEquals("toto", responseBody.getFirstName());
        assertEquals("tata", responseBody.getLastName());
        assertEquals(id, responseBody.getId());
        assertEquals(isAdmin, responseBody.getAdmin());
        assertEquals("Bearer", responseBody.getType());
        assertNotNull(responseBody.getToken());

    }


    @Test
    void registerUser() {
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setEmail("toto.com");
        signupRequest.setFirstName("toto");
        signupRequest.setLastName("tata");
        signupRequest.setPassword("password");

        when(userRepository.existsByEmail("toto.com")).thenReturn(false);
        when(passwordEncoder.encode("password")).thenReturn("password");
        when(userRepository.save(any(User.class))).thenReturn(User.builder().id(1L).email("toto.com").firstName("toto")
                .lastName("tata").password("password").admin(false).build());

        AuthController authController = new AuthController(authenticationManager, passwordEncoder, jwtUtils,
                userRepository);
        ResponseEntity<?> response = authController.registerUser(signupRequest);
        MessageResponse responseBody = (MessageResponse) response.getBody();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("User registered successfully!", responseBody.getMessage());

    }
}
