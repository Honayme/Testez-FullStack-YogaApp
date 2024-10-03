package com.openclassrooms.starterjwt.mapper;

import com.openclassrooms.starterjwt.dto.SessionDto;
import com.openclassrooms.starterjwt.dto.TeacherDto;
import com.openclassrooms.starterjwt.models.Session;
import com.openclassrooms.starterjwt.models.Teacher;
import com.openclassrooms.starterjwt.models.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class SessionMapperTest {

    @Test
    void toEntity() {

        SessionDto sessionDto = new SessionDto();
        sessionDto.setId(1L);
        sessionDto.setDescription("Test toto");

        SessionMapper sessionMapper = new SessionMapperImpl();
        Session session = sessionMapper.toEntity(sessionDto);
        assertEquals(sessionDto.getId(), session.getId());
        assertEquals(sessionDto.getDescription(), session.getDescription());
    }

    @Test
    void toDto() {
        Session session = new Session();
        session.setId(1L);
        session.setDescription("Test toto");

        SessionMapper sessionMapper = new SessionMapperImpl();
        SessionDto sessionDto = sessionMapper.toDto(session);
        assertEquals(session.getId(), sessionDto.getId());
        assertEquals(session.getDescription(), sessionDto.getDescription());
    }

    @Test
    void toDtoList() {
        Session session = new Session();
        session.setId(1L);
        session.setDescription("Test toto");

        Session session2 = new Session();
        session2.setId(2L);
        session2.setDescription("Test tata");

        List<Session> sessions = new ArrayList<>();
        sessions.add(session);
        sessions.add(session2);

        SessionMapper sessionMapper = new SessionMapperImpl();
        List<SessionDto> sessionDtos = sessionMapper.toDto(sessions);
        assertEquals(sessions.size(), sessionDtos.size());
    }

    @Test
    void toEntityList() {
        SessionDto sessionDto = new SessionDto();
        sessionDto.setId(1L);
        sessionDto.setDescription("Test toto");

        SessionDto sessionDto2 = new SessionDto();
        sessionDto2.setId(2L);
        sessionDto2.setDescription("Test tata");

        List<SessionDto> sessionDtos = new ArrayList<>();
        sessionDtos.add(sessionDto);
        sessionDtos.add(sessionDto2);

        SessionMapper sessionMapper = new SessionMapperImpl();
        List<Session> sessions = sessionMapper.toEntity(sessionDtos);
        assertEquals(sessionDtos.size(), sessions.size());
    }



}