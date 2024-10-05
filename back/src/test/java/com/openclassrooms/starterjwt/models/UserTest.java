package com.openclassrooms.starterjwt.models;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class UserTest {

    @Test
    void getId() {
        Long id = 1L;
        User user = new User();
        user.setId(id);
        Long retrievedId = user.getId();
        assertEquals(id, retrievedId);
    }

    @Test
    void getEmail() {
        String email = "toto.com";
        User user = new User();
        user.setEmail(email);
        String retrievedEmail = user.getEmail();
        assertEquals(email, retrievedEmail);

    }

    @Test
    void getLastName() {
        String lastName = "Doe";
        User user = new User();
        user.setLastName(lastName);
        String retrievedLastName = user.getLastName();
        assertEquals(lastName, retrievedLastName);
    }

    @Test
    void getFirstName() {
        String firstName = "John";
        User user = new User();
        user.setFirstName(firstName);
        String retrievedFirstName = user.getFirstName();
        assertEquals(firstName, retrievedFirstName);
    }

    @Test
    void getPassword() {
        String password = "password";
        User user = new User();
        user.setPassword(password);
        String retrievedPassword = user.getPassword();
        assertEquals(password, retrievedPassword);
    }

    @Test
    void isAdmin() {
        boolean admin = true;
        User user = new User();
        user.setAdmin(admin);
        boolean retrievedAdmin = user.isAdmin();
        assertEquals(admin, retrievedAdmin);
    }

    @Test
    void getCreatedAt() {

        LocalDateTime createdAt = LocalDateTime.now();

        User user = new User();
        user.setCreatedAt(createdAt);
        LocalDateTime retrievedCreatedAt = user.getCreatedAt();
        assertEquals(createdAt, retrievedCreatedAt);

    }

    @Test
    void getUpdatedAt() {

        LocalDateTime updatedAt = LocalDateTime.now();

        User user = new User();
        user.setUpdatedAt(updatedAt);
        LocalDateTime retrievedUpdatedAt = user.getUpdatedAt();
        assertEquals(updatedAt, retrievedUpdatedAt);

    }

    @Test
    void setId() {
        Long id = 1L;
        User user = new User();
        user.setId(id);
        Long retrievedId = user.getId();
        assertEquals(id, retrievedId);
    }

    @Test
    void setEmail() {
        String email = "toto.com";
        User user = new User();
        user.setEmail(email);
        String retrievedEmail = user.getEmail();
        assertEquals(email, retrievedEmail);
    }

    @Test
    void setLastName() {
        String lastName = "toto";
        User user = new User();
        user.setLastName(lastName);
        String retrievedLastName = user.getLastName();
        assertEquals(lastName, retrievedLastName);
    }

    @Test
    void setFirstName() {
        String firstName = "tata";
        User user = new User();
        user.setFirstName(firstName);
        String retrievedFirstName = user.getFirstName();
        assertEquals(firstName, retrievedFirstName);
    }

    @Test
    void setPassword() {
        String password = "password";
        User user = new User();
        user.setPassword(password);
        String retrievedPassword = user.getPassword();
        assertEquals(password, retrievedPassword);
    }

    @Test
    void setAdmin() {
        boolean admin = true;
        User user = new User();
        user.setAdmin(admin);
        boolean retrievedAdmin = user.isAdmin();
        assertEquals(admin, retrievedAdmin);
    }

    @Test
    void setCreatedAt() {
        LocalDateTime createdAt = LocalDateTime.now();
        User user = new User();
        user.setCreatedAt(createdAt);
        LocalDateTime retrievedCreatedAt = user.getCreatedAt();
        assertEquals(createdAt, retrievedCreatedAt);
    }

    @Test
    void setUpdatedAt() {
        LocalDateTime updatedAt = LocalDateTime.now();
        User user = new User();
        user.setUpdatedAt(updatedAt);
        LocalDateTime retrievedUpdatedAt = user.getUpdatedAt();
        assertEquals(updatedAt, retrievedUpdatedAt);
    }

    @Test
    void testEquals() {
        LocalDateTime date = LocalDateTime.now();

        User user = new User();
        user.setCreatedAt(date);
        user.setEmail("toto.com");
        user.setLastName("toto");
        user.setFirstName("tata");
        user.setPassword("password");
        user.setAdmin(true);

        User user1 = new User();
        user1.setCreatedAt(date);
        user1.setEmail("toto.com");
        user1.setLastName("toto");
        user1.setFirstName("tata");
        user1.setPassword("password");
        user1.setAdmin(true);
        assertEquals(user, user1);


    }

    @Test
    void canEqual() {
        LocalDateTime date = LocalDateTime.now();

        User user = new User();
        user.setCreatedAt(date);
        user.setEmail("toto.com");
        user.setLastName("toto");
        user.setFirstName("tata");
        user.setPassword("password");
        user.setAdmin(true);

        User user1 = new User();
        user1.setCreatedAt(date);
        user1.setEmail("toto.com");
        user1.setLastName("toto");
        user1.setFirstName("tata");
        user1.setPassword("password");
        user1.setAdmin(true);
        assertTrue(user.canEqual(user1));
    }

    @Test
    void testHashCode() {
        LocalDateTime date = LocalDateTime.now();

        User user = new User();
        user.setCreatedAt(date);
        user.setEmail("toto.com");
        user.setLastName("toto");
        user.setFirstName("tata");
        user.setPassword("password");
        user.setAdmin(true);

        User user1 = new User();
        user1.setCreatedAt(date);
        user1.setEmail("toto.com");
        user1.setLastName("toto");
        user1.setFirstName("tata");
        user1.setPassword("password");
        user1.setAdmin(true);
        assertEquals(user.hashCode(), user1.hashCode());
    }

    @Test
    void testToString() {
        LocalDateTime date = LocalDateTime.now();

        User user = new User();
        user.setCreatedAt(date);
        user.setEmail("toto.com");
        user.setLastName("toto");
        user.setFirstName("tata");
        user.setPassword("password");
        user.setAdmin(true);
        assertEquals(user.toString(), "User(id=null, email=toto.com, lastName=toto, firstName=tata, password=password, admin=true, createdAt=" + date + ", updatedAt=null)");
    }

    @Test
    void builder() {
        LocalDateTime date = LocalDateTime.now();
        User user = User.builder()
                .id(1L)
                .email("toto@gmail.com")
                .lastName("toto")
                .firstName("tata")
                .password("password")
                .admin(true)
                .createdAt(date)
                .updatedAt(LocalDateTime.now())
                .build();
        assertEquals(user.getId(), 1L);
        assertEquals(user.getEmail(), "toto@gmail.com");
        assertEquals(user.getLastName(), "toto");
        assertEquals(user.getFirstName(), "tata");
        assertEquals(user.getPassword(), "password");
        assertEquals(user.isAdmin(), true);
        assertEquals(user.getCreatedAt(), date);
    }

    // faire un test User(String, String, String, String, boolean)
    @Test
    void user() {
        User user = User.builder()
                .email("toto.com")
                .lastName("toto")
                .firstName("tata")
                .password("password")
                .admin(true)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        assertEquals(user.getEmail(), "toto.com");
        assertEquals(user.getLastName(), "toto");
        assertEquals(user.getFirstName(), "tata");
        assertEquals(user.getPassword(), "password");
        assertEquals(user.isAdmin(), true);

    }


}