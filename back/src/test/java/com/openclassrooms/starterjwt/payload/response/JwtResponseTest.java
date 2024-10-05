package com.openclassrooms.starterjwt.payload.response;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class JwtResponseTest {

    @Test
    void jwtResponse() {
        // Arrange
        String accessToken = "someToken";
        Long id = 1L;
        String username = "testUser";
        String firstName = "John";
        String lastName = "Doe";
        Boolean admin = true;

        // Act
        JwtResponse jwtResponse = new JwtResponse(accessToken, id, username, firstName, lastName, admin);

        // Assert
        assertEquals(accessToken, jwtResponse.getToken());
        assertEquals("Bearer", jwtResponse.getType());
        assertEquals(id, jwtResponse.getId());
        assertEquals(username, jwtResponse.getUsername());
        assertEquals(firstName, jwtResponse.getFirstName());
        assertEquals(lastName, jwtResponse.getLastName());
        assertEquals(admin, jwtResponse.getAdmin());
    }

    @Test
    void jwtResponse_Null() {
        // Arrange
        String accessToken = null;
        Long id = null;
        String username = null;
        String firstName = null;
        String lastName = null;
        Boolean admin = null;

        // Act
        JwtResponse jwtResponse = new JwtResponse(accessToken, id, username, firstName, lastName, admin);

        // Assert
        assertNull(jwtResponse.getToken());
        assertEquals("Bearer", jwtResponse.getType());
        assertNull(jwtResponse.getId());
        assertNull(jwtResponse.getUsername());
        assertNull(jwtResponse.getFirstName());
        assertNull(jwtResponse.getLastName());
        assertNull(jwtResponse.getAdmin());
    }

    @Test
    void jwtResponse_Empty() {
        // Arrange
        String accessToken = "";
        Long id = 0L;
        String username = "";
        String firstName = "";
        String lastName = "";
        Boolean admin = false;

        // Act
        JwtResponse jwtResponse = new JwtResponse(accessToken, id, username, firstName, lastName, admin);

        // Assert
        assertEquals(accessToken, jwtResponse.getToken());
        assertEquals("Bearer", jwtResponse.getType());
        assertEquals(id, jwtResponse.getId());
        assertEquals(username, jwtResponse.getUsername());
        assertEquals(firstName, jwtResponse.getFirstName());
        assertEquals(lastName, jwtResponse.getLastName());
        assertEquals(admin, jwtResponse.getAdmin());
    }

    @Test
    void setToken() {
        // Arrange
        JwtResponse jwtResponse = new JwtResponse("Original Token", 1L, "testUser", "John", "Doe", true);

        // Act
        String newToken = "New Token";
        jwtResponse.setToken(newToken);

        assertEquals(newToken, jwtResponse.getToken());
    }

    @Test
    void setAdmin() {
        // Arrange
        JwtResponse jwtResponse = new JwtResponse("Original Token", 1L, "testUser", "John", "Doe", true);

        // Act
        Boolean newAdmin = false;
        jwtResponse.setAdmin(newAdmin);

        assertEquals(newAdmin, jwtResponse.getAdmin());
    }

    @Test
    void setID() {
        // Arrange
        JwtResponse jwtResponse = new JwtResponse("Original Token", 1L, "testUser", "John", "Doe", true);

        // Act
        Long newID = 2L;
        jwtResponse.setId(newID);

        assertEquals(newID, jwtResponse.getId());
    }

    @Test
    void setUsername() {
        // Arrange
        JwtResponse jwtResponse = new JwtResponse("Original Token", 1L, "testUser", "John", "Doe", true);

        // Act
        String newUsername = "newUser";
        jwtResponse.setUsername(newUsername);

        assertEquals(newUsername, jwtResponse.getUsername());
    }

    @Test
    void setFirstName() {
        // Arrange
        JwtResponse jwtResponse = new JwtResponse("Original Token", 1L, "testUser", "John", "Doe", true);

        // Act
        String newFirstName = "Jane";
        jwtResponse.setFirstName(newFirstName);

        assertEquals(newFirstName, jwtResponse.getFirstName());
    }

    @Test
    void setLastName() {
        // Arrange
        JwtResponse jwtResponse = new JwtResponse("Original Token", 1L, "testUser", "John", "Doe", true);

        // Act
        String newLastName = "Smith";
        jwtResponse.setLastName(newLastName);

        assertEquals(newLastName, jwtResponse.getLastName());
    }

    @Test
    void setType() {
        // Arrange
        JwtResponse jwtResponse = new JwtResponse("Original Token", 1L, "testUser", "John", "Doe", true);

        // Act
        String newType = "New Type";
        jwtResponse.setType(newType);

        assertEquals(newType, jwtResponse.getType());
    }


}