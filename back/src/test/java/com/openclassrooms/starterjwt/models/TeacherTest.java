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
class TeacherTest {

    @Test
    void getId() {
        Long id = 1L;
        Teacher teacher = new Teacher();
        teacher.setId(id);
        Long retrievedId = teacher.getId();
        assertEquals(id, retrievedId);

    }

    @Test
    void getLastName() {
        String lastName = "Doe";
        Teacher teacher = new Teacher();
        teacher.setLastName(lastName);
        String retrievedLastName = teacher.getLastName();
        assertEquals(lastName, retrievedLastName);
    }

    @Test
    void getFirstName() {
        String firstName = "John";
        Teacher teacher = new Teacher();
        teacher.setFirstName(firstName);
        String retrievedFirstName = teacher.getFirstName();
        assertEquals(firstName, retrievedFirstName);
    }

    @Test
    void getCreatedAt() {
        LocalDateTime createdAt = LocalDateTime.now();
        Teacher teacher = new Teacher();
        teacher.setCreatedAt(createdAt);
        LocalDateTime retrievedCreatedAt = teacher.getCreatedAt();
        assertEquals(createdAt, retrievedCreatedAt);

    }

    @Test
    void getUpdatedAt() {
        LocalDateTime updatedAt = LocalDateTime.now();
        Teacher teacher = new Teacher();
        teacher.setUpdatedAt(updatedAt);
        LocalDateTime retrievedUpdatedAt = teacher.getUpdatedAt();
        assertEquals(updatedAt, retrievedUpdatedAt);
    }

    @Test
    void setId() {
        Long id = 1L;
        Teacher teacher = new Teacher();
        teacher.setId(id);
        assertEquals(id, teacher.getId());
    }

    @Test
    void setLastName() {
        String lastName = "toto";
        Teacher teacher = new Teacher();
        teacher.setLastName(lastName);
        assertEquals(lastName, teacher.getLastName());
    }

    @Test
    void setFirstName() {
        String firstName = "toto";
        Teacher teacher = new Teacher();
        teacher.setFirstName(firstName);
        assertEquals(firstName, teacher.getFirstName());
    }

    @Test
    void setCreatedAt() {
        LocalDateTime date = LocalDateTime.now();
        Teacher teacher = new Teacher();
        teacher.setCreatedAt(date);
        LocalDateTime retrievedDate = teacher.getCreatedAt();
        assertEquals(date, retrievedDate);
    }

    @Test
    void setUpdatedAt() {
        LocalDateTime date = LocalDateTime.now();
        Teacher teacher = new Teacher();
        teacher.setUpdatedAt(date);
        LocalDateTime retrievedDate = teacher.getUpdatedAt();
        assertEquals(date, retrievedDate);
    }

    @Test
    void testEquals() {

        LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime updatedAt = LocalDateTime.now();

        Teacher teacher = new Teacher();
        teacher.setId(1L);
        teacher.setLastName("toto");
        teacher.setFirstName("tata");
        teacher.setCreatedAt(createdAt);
        teacher.setUpdatedAt(updatedAt);

        Teacher teacher1 = new Teacher();
        teacher1.setId(1L);
        teacher1.setLastName("toto");
        teacher1.setFirstName("tata");
        teacher1.setCreatedAt(createdAt);
        teacher1.setUpdatedAt(updatedAt);

        assertEquals(teacher, teacher1);

    }

    @Test
    void canEqual() {

        LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime updatedAt = LocalDateTime.now();

        Teacher teacher = new Teacher();
        teacher.setId(1L);
        teacher.setLastName("toto");
        teacher.setFirstName("tata");
        teacher.setCreatedAt(createdAt);
        teacher.setUpdatedAt(updatedAt);

        Teacher teacher1 = new Teacher();
        teacher1.setId(1L);
        teacher1.setLastName("toto");
        teacher1.setFirstName("tata");
        teacher1.setCreatedAt(createdAt);
        teacher1.setUpdatedAt(updatedAt);

        assertTrue(teacher.canEqual(teacher1));
    }

    @Test
    void testHashCode() {

        LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime updatedAt = LocalDateTime.now();

        Teacher teacher = new Teacher();
        teacher.setId(1L);
        teacher.setLastName("toto");
        teacher.setFirstName("tata");
        teacher.setCreatedAt(createdAt);
        teacher.setUpdatedAt(updatedAt);

        Teacher teacher1 = new Teacher();
        teacher1.setId(1L);
        teacher1.setLastName("toto");
        teacher1.setFirstName("tata");
        teacher1.setCreatedAt(createdAt);
        teacher1.setUpdatedAt(updatedAt);

        assertEquals(teacher.hashCode(), teacher1.hashCode());
    }

    @Test
    void testToString() {

        LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime updatedAt = LocalDateTime.now();

        Teacher teacher = new Teacher();
        teacher.setId(1L);
        teacher.setLastName("toto");
        teacher.setFirstName("tata");
        teacher.setCreatedAt(createdAt);
        teacher.setUpdatedAt(updatedAt);

        String result = teacher.toString();
        assertEquals(result, teacher.toString());
    }

    @Test
    void builder() {
        LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime updatedAt = LocalDateTime.now();
        Teacher teacher = Teacher.builder()
                .id(1L)
                .lastName("toto")
                .firstName("tata")
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .build();
        assertEquals(1L, teacher.getId());
        assertEquals("toto", teacher.getLastName());
        assertEquals("tata", teacher.getFirstName());
        assertEquals(createdAt, teacher.getCreatedAt());
        assertEquals(updatedAt, teacher.getUpdatedAt());
    }
}