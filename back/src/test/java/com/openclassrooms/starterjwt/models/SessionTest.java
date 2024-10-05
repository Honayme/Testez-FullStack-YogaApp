package com.openclassrooms.starterjwt.models;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SessionTest {

    @Test
    void getId() {
        Long id = 1L;
        Session session = new Session();
        session.setId(id);
        Long retrievedId = session.getId();
        assertEquals(id, retrievedId);
    }

    @Test
    void getName() {
        String name = "Maths";
        Session session = new Session();
        session.setName(name);
        String retrievedName = session.getName();
        assertEquals(name, retrievedName);
    }

    @Test
    void getDate() {
        Date date = new Date();
        Session session = new Session();
        session.setDate(date);
        Date retrievedDate = session.getDate();
        assertEquals(date, retrievedDate);

    }

    @Test
    void getDescription() {
        String description = "Maths";
        Session session = new Session();
        session.setDescription(description);
        String retrievedDescription = session.getDescription();
        assertEquals(description, retrievedDescription);
    }

    @Test
    void getTeacher() {
        Long teacher = 1L;
        Teacher teacher1 = new Teacher();
        teacher1.setId(teacher);
        Session session = new Session();
        session.setTeacher(teacher1);
        Teacher retrievedTeacher = session.getTeacher();
        assertEquals(teacher1, retrievedTeacher);


    }

    @Test
    void getUsers() {
        List<User> users = new ArrayList<>();
        User user = new User();
        user.setId(1L);
        users.add(user);
        Session session = new Session();
        session.setUsers(users);
        List<User> retrievedUsers = session.getUsers();
        assertEquals(users, retrievedUsers);
    }

    @Test
    void getCreatedAt() {
        LocalDateTime date = LocalDateTime.now();
        Session session = new Session();
        session.setCreatedAt(date);
        LocalDateTime retrievedDate = session.getCreatedAt();
        assertEquals(date, retrievedDate);
    }

    @Test
    void getUpdatedAt() {
        LocalDateTime date = LocalDateTime.now();
        Session session = new Session();
        session.setUpdatedAt(date);
        LocalDateTime retrievedDate = session.getUpdatedAt();
        assertEquals(date, retrievedDate);
    }

    @Test
    void setId() {
        Long id = 1L;
        Session session = new Session();
        session.setId(id);
        Long retrievedId = session.getId();
        assertEquals(id, retrievedId);
    }

    @Test
    void setName() {
        String name = "toto";
        Session session = new Session();
        session.setName(name);
        String retrievedName = session.getName();
        assertEquals(name, retrievedName);
    }

    @Test
    void setDate() {
        Date date = new Date();
        Session session = new Session();
        session.setDate(date);
        Date retrievedDate = session.getDate();
        assertEquals(date, retrievedDate);
    }

    @Test
    void setDescription() {
        String description = "toto session";
        Session session = new Session();
        session.setDescription(description);
        String retrievedDescription = session.getDescription();
        assertEquals(description, retrievedDescription);
    }

    @Test
    void setTeacher() {
        Long teacher = 1L;
        Teacher teacher1 = new Teacher();
        teacher1.setId(teacher);
        Session session = new Session();
        session.setTeacher(teacher1);
        Teacher retrievedTeacher = session.getTeacher();
        assertEquals(teacher1, retrievedTeacher);
    }

    @Test
    void setUsers() {
        List<User> users = new ArrayList<>();
        User user = new User();
        user.setId(1L);
        users.add(user);
        Session session = new Session();
        session.setUsers(users);
        List<User> retrievedUsers = session.getUsers();
        assertEquals(users, retrievedUsers);
    }

    @Test
    void setCreatedAt() {
        LocalDateTime date = LocalDateTime.now();
        Session session = new Session();
        session.setCreatedAt(date);
        LocalDateTime retrievedDate = session.getCreatedAt();
        assertEquals(date, retrievedDate);
    }

    @Test
    void setUpdatedAt() {
        LocalDateTime date = LocalDateTime.now();
        Session session = new Session();
        session.setUpdatedAt(date);
        LocalDateTime retrievedDate = session.getUpdatedAt();
        assertEquals(date, retrievedDate);
    }

    @Test
    void testEquals() {

        LocalDateTime date = LocalDateTime.now();

        Session session = new Session();
        session.setCreatedAt(date);
        session.setDate(new Date());
        session.setDescription("toto");
        session.setId(1L);
        session.setName("toto");
        session.setTeacher(new Teacher());
        session.setUpdatedAt(date);
        session.setUsers(new ArrayList<>());

        Session session1 = new Session();
        session1.setCreatedAt(date);
        session1.setDate(new Date());
        session1.setDescription("toto");
        session1.setId(1L);
        session1.setName("toto");
        session1.setTeacher(new Teacher());
        session1.setUpdatedAt(date);
        session1.setUsers(new ArrayList<>());

        assertEquals(session, session1);

    }

    @Test
    void canEqual() {

        LocalDateTime date = LocalDateTime.now();

        Session session = new Session();
        session.setCreatedAt(date);
        session.setDate(new Date());
        session.setDescription("toto");
        session.setId(1L);
        session.setName("toto");
        session.setTeacher(new Teacher());
        session.setUpdatedAt(date);
        session.setUsers(new ArrayList<>());

        Session session1 = new Session();
        session1.setCreatedAt(date);
        session1.setDate(new Date());
        session1.setDescription("toto");
        session1.setId(1L);
        session1.setName("toto");
        session1.setTeacher(new Teacher());
        session1.setUpdatedAt(date);
        session1.setUsers(new ArrayList<>());

        assertTrue(session.canEqual(session1));

    }

    @Test
    void testHashCode() {

        LocalDateTime date = LocalDateTime.now();

        Session session = new Session();
        session.setCreatedAt(date);
        session.setDate(new Date());
        session.setDescription("toto");
        session.setId(1L);
        session.setName("toto");
        session.setTeacher(new Teacher());
        session.setUpdatedAt(date);
        session.setUsers(new ArrayList<>());

        Session session1 = new Session();
        session1.setCreatedAt(date);
        session1.setDate(new Date());
        session1.setDescription("toto");
        session1.setId(1L);
        session1.setName("toto");
        session1.setTeacher(new Teacher());
        session1.setUpdatedAt(date);
        session1.setUsers(new ArrayList<>());

        assertEquals(session.hashCode(), session1.hashCode());
    }

    @Test
    void testToString() {

        LocalDateTime date = LocalDateTime.now();

        Session session = new Session();
        session.setCreatedAt(date);
        session.setDate(new Date());
        session.setDescription("toto");
        session.setId(1L);
        session.setName("toto");
        session.setTeacher(new Teacher());
        session.setUpdatedAt(date);
        session.setUsers(new ArrayList<>());

        assertEquals(session.toString(), session.toString());
    }

    @Test
    void builder() {
        LocalDateTime date = LocalDateTime.now();
        Session session = Session.builder()
                .id(1L)
                .name("toto")
                .date(new Date())
                .description("toto")
                .teacher(new Teacher())
                .users(new ArrayList<>())
                .createdAt(date)
                .updatedAt(date)
                .build();

        assertEquals(1L, session.getId());
        assertEquals("toto", session.getName());
        assertEquals("toto", session.getDescription());
        assertEquals(date, session.getCreatedAt());
        assertEquals(date, session.getUpdatedAt());
    }
}