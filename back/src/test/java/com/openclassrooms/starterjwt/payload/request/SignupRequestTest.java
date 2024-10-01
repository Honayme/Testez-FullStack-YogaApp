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
class SignupRequestTest {

    @Test
    void getEmail() {
        // Arrange
        String email = "test@example.com";
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setEmail(email);

        // Act
        String retrievedEmail = signupRequest.getEmail();

        // Assert
        assertEquals(email, retrievedEmail);
    }

    @Test
    void getFirstName() {
        // Arrange
        String firstName = "John";
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setFirstName(firstName);

        // Act
        String retrievedFirstName = signupRequest.getFirstName();

        // Assert
        assertEquals(firstName, retrievedFirstName);
    }

    @Test
    void getLastName() {
        // Arrange
        String lastName = "Doe";
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setLastName(lastName);

        // Act
        String retrievedLastName = signupRequest.getLastName();

        // Assert
        assertEquals(lastName, retrievedLastName);
    }

    @Test
    void getPassword() {
        // Arrange
        String password = "securePassword";
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setPassword(password);

        // Act
        String retrievedPassword = signupRequest.getPassword();

        // Assert
        assertEquals(password, retrievedPassword);
    }

    @Test
    void setEmail() {
        // Arrange
        String email = "test@example.com";
        SignupRequest signupRequest = new SignupRequest();

        // Act
        signupRequest.setEmail(email);

        // Assert
        assertEquals(email, signupRequest.getEmail());
    }

    @Test
    void setFirstName() {
        // Arrange
        String firstName = "John";
        SignupRequest signupRequest = new SignupRequest();

        // Act
        signupRequest.setFirstName(firstName);

        // Assert
        assertEquals(firstName, signupRequest.getFirstName());
    }

    @Test
    void setLastName() {
        // Arrange
        String lastName = "Doe";
        SignupRequest signupRequest = new SignupRequest();

        // Act
        signupRequest.setLastName(lastName);

        // Assert
        assertEquals(lastName, signupRequest.getLastName());
    }

    @Test
    void setPassword() {
        // Arrange
        String password = "securePassword";
        SignupRequest signupRequest = new SignupRequest();

        // Act
        signupRequest.setPassword(password);

        // Assert
        assertEquals(password, signupRequest.getPassword());
    }

    @Test
    void testEquals() {

        String password = "securePassword";
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setEmail("toto.com");
        signupRequest.setFirstName("toto");
        signupRequest.setLastName("tata");
        signupRequest.setPassword(password);
        assertTrue(signupRequest.equals(signupRequest));

    }


    @Test
    void canEqual() {

        String password = "securePassword";
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setEmail("toto.com");
        signupRequest.setFirstName("toto");
        signupRequest.setLastName("tata");
        signupRequest.setPassword(password);
        assertTrue(signupRequest.canEqual(signupRequest));

    }

    @Test
    void testHashCode() {

        String password = "securePassword";
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setEmail("toto.com");
        signupRequest.setFirstName("toto");
        signupRequest.setLastName("tata");
        signupRequest.setPassword(password);
        assertEquals(signupRequest.hashCode(), signupRequest.hashCode());

    }

    @Test
    void testToString() {

        String password = "securePassword";
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setEmail("toto.com");
        signupRequest.setFirstName("toto");
        signupRequest.setLastName("tata");
        signupRequest.setPassword(password);
        assertEquals(signupRequest.toString(), signupRequest.toString());

    }

    @Test
    void equals() {
        // Arrange
        SignupRequest signupRequest1 = new SignupRequest();
        signupRequest1.setEmail("toto.com");
        signupRequest1.setFirstName("toto");
        signupRequest1.setLastName("tata");
        signupRequest1.setPassword("securePassword");

        SignupRequest signupRequest2 = new SignupRequest();
        signupRequest2.setEmail("toto.com");
        signupRequest2.setFirstName("toto");
        signupRequest2.setLastName("tata");
        signupRequest2.setPassword("securePassword");

        // Act
        boolean result = signupRequest1.equals(signupRequest2);

        // Assert

        assertTrue(result);

    }


}