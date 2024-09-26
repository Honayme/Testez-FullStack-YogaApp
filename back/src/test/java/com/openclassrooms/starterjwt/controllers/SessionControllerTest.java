package com.openclassrooms.starterjwt.controllers;

import com.openclassrooms.starterjwt.dto.SessionDto;
import com.openclassrooms.starterjwt.mapper.SessionMapper;
import com.openclassrooms.starterjwt.models.Session;
import com.openclassrooms.starterjwt.services.SessionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ActiveProfiles;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class SessionControllerTest {

    @Mock
    private SessionService sessionService;

    @Mock
    private SessionMapper sessionMapper;

    @InjectMocks
    private SessionController sessionController;

    private List<SessionDto> sessionDtos;
    private List<Session> sessions;

    private SessionDto sessionDto;
    private Session session;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);
        sessionController = new SessionController(sessionService, sessionMapper);
        Long id = 1L;
        String description = "Test toto";
        session = new Session();
        session.setId(id);
        session.setDescription(description);

        sessionDto = sessionMapper.toDto(session);
        sessionDtos = Collections.singletonList(sessionDto);
        sessions = Collections.singletonList(session);

    }



    @Test
    void findById() {

        when(sessionService.getById(1L)).thenReturn(session);
        when(sessionMapper.toDto(session)).thenReturn(sessionDto);

        ResponseEntity<?> response = sessionController.findById("1");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(sessionDto, response.getBody());

    }

    @Test
    void findAll() {
        when(sessionService.findAll()).thenReturn(sessions);
        when(sessionMapper.toDto(sessions)).thenReturn(sessionDtos);

        ResponseEntity<?> response = sessionController.findAll();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(sessionDtos, response.getBody());
    }

    @Test
    void create() {
        when(sessionMapper.toEntity(sessionDto)).thenReturn(session);
        when(sessionService.create(session)).thenReturn(session);
        when(sessionMapper.toDto(session)).thenReturn(sessionDto);

        ResponseEntity<?> response = sessionController.create(sessionDto);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(sessionDto, response.getBody());
    }

    @Test
    void update() {
        when(sessionMapper.toEntity(sessionDto)).thenReturn(session);
        when(sessionService.update(1L, session)).thenReturn(session);
        when(sessionMapper.toDto(session)).thenReturn(sessionDto);

        ResponseEntity<?> response = sessionController.update("1", sessionDto);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(sessionDto, response.getBody());
    }


    @Test
    void save() {
        when(sessionService.getById(1L)).thenReturn(session);
        ResponseEntity<?> response = sessionController.save("1");
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void participate() {
        ResponseEntity<?> response = sessionController.participate("1", "1");
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void noLongerParticipate() {
        ResponseEntity<?> response = sessionController.noLongerParticipate("1", "1");
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void delete_should_return_not_found() {
        Long id = 1L;
        when(sessionService.getById(1L)).thenReturn(null);
        ResponseEntity<?> responseEntity = sessionController.save(id.toString());
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }





}