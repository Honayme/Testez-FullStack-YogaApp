package com.openclassrooms.starterjwt.payload.request;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class LoginRequestTest {

    @Test
    void getEmail() {
        // Arrange
        String email = "toto.com";
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(email);
        String retrievedEmail = loginRequest.getEmail();
        assertEquals(email, retrievedEmail);

    }

    @Test
    void setEmail() {

        // Arrange
        String email = "toto.com";
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(email);
        assertEquals(email, loginRequest.getEmail());
    }

    @Test
    void getPassword() {
        // Arrange
        String password = "securePassword";
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setPassword(password);
        String retrievedPassword = loginRequest.getPassword();
        assertEquals(password, retrievedPassword);
    }

    @Test
    void setPassword() {
        // Arrange
        String password = "securePassword";
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setPassword(password);
        assertEquals(password, loginRequest.getPassword());
    }
}