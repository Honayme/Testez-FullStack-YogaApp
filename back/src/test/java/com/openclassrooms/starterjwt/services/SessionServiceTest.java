package com.openclassrooms.starterjwt.services;

import com.openclassrooms.starterjwt.exception.BadRequestException;
import com.openclassrooms.starterjwt.exception.NotFoundException;
import com.openclassrooms.starterjwt.models.Session;
import com.openclassrooms.starterjwt.models.User;
import com.openclassrooms.starterjwt.repository.SessionRepository;
import com.openclassrooms.starterjwt.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class SessionServiceTest {

    @InjectMocks
    private SessionService sessionService;

    @Mock
    private SessionRepository sessionRepository;

    @Mock
    private UserRepository userRepository;

    @Test
    void create() {

        // on cree un objet session
        Session session = new Session();
        // on lui donne un id
        session.setId(1L);
        // le when permet de dire que lorsque l'on appelle la méthode save de sessionRepository avec n'importe quel objet session en paramètre, on renvoie l'objet session
        when(sessionRepository.save(any(Session.class))).thenReturn(session);
        // on instancie un objet sessionService avec les mocks sessionRepository et userRepository
        SessionService sessionService = new SessionService(sessionRepository, userRepository);
        // on appelle la méthode create de sessionService avec l'objet session en paramètre
        Session createdSession = sessionService.create(session);
        // on vérifie que l'objet session retourné par la méthode create de sessionService est bien le même que l'objet session que l'on a créé
        Assertions.assertEquals(session.getId(), createdSession.getId());

    }

    @Test
    void delete() {


        Session session = new Session();
        session.setId(1L);
        // le doNothing permet de dire que lorsque l'on appelle la méthode deleteById de sessionRepository avec l'id 1 en paramètre, on ne fait rien (on ne renvoie rien)
        doNothing().when(sessionRepository).deleteById(1L);
        //
        SessionService sessionService = new SessionService(sessionRepository, userRepository);
        // on appelle la méthode delete de sessionService avec l'id 1 en paramètre
        sessionService.delete(1L);
        // on vérifie que la méthode deleteById de sessionRepository a bien été appelée une fois avec l'id 1 en paramètre
        verify(sessionRepository, times(1)).deleteById(1L);

    }

    @Test
    void findAll() {
        // on crée une liste de sessions
        List<Session> sessions = new ArrayList<>();
        // on crée une session
        Session session = new Session();
        // on lui donne un id
        session.setId(1L);
        // on ajoute la session à la liste de sessions
        sessions.add(session);
        // le when permet de dire que lorsque l'on appelle la méthode findAll de sessionRepository, on renvoie la liste de sessions
        when(sessionRepository.findAll()).thenReturn(sessions);
        // on instancie un objet sessionService avec les mocks sessionRepository et userRepository
        SessionService sessionService = new SessionService(sessionRepository, userRepository);
        // on appelle la méthode findAll de sessionService
        List<Session> foundSessions = sessionService.findAll();
        // on vérifie que la liste de sessions retournée par la méthode findAll de sessionService est bien la même que la liste de sessions que l'on a créée
        assertThat(foundSessions).hasSize(1);
        // on vérifie que la session retournée par la méthode findAll de sessionService est bien la même que la session que l'on a créée
        assertThat(foundSessions.get(0).getId()).isEqualTo(1L);

    }

    @Test
    void getById() {
        Session session = new Session();
        session.setId(1L);
        when(sessionRepository.findById(1L)).thenReturn(java.util.Optional.of(session));
        SessionService sessionService = new SessionService(sessionRepository, userRepository);
        Session foundSession = sessionService.getById(1L);
        assertThat(foundSession.getId()).isEqualTo(1L);

    }

    @Test
    void update() {

        Session session = Session.builder().description("description").date(new Date()).name("name").id(1L).build();

        when(sessionRepository.save(any(Session.class))).thenReturn(session);
        SessionService sessionService = new SessionService(sessionRepository, userRepository);
        Session updatedSession = sessionService.update(1L, session);
        assertThat(updatedSession.getId()).isEqualTo(1L);
    }

    @Test
    void participate() {

        Session session = Session.builder().description("description").date(new Date()).name("name").id(1L).users(new ArrayList<>()).build();
        when(sessionRepository.findById(1L)).thenReturn(Optional.of(session));

        User user = User.builder().id(2L).email("toto.com").firstName("toto").lastName("tata").admin(false).password("").build();
        when(userRepository.findById(2L)).thenReturn(Optional.of(user));

        SessionService sessionService = new SessionService(sessionRepository, userRepository);
        sessionService.participate(session.getId(), user.getId());
        assertThat(session.getUsers()).hasSize(1);

    }
    @Test
    void participateNotFound() {

        when(sessionRepository.findById(1L)).thenReturn(Optional.ofNullable(null));

        User user = User.builder().id(2L).email("toto.com").firstName("toto").lastName("tata").admin(false).password("").build();
        when(userRepository.findById(2L)).thenReturn(Optional.of(user));

        SessionService sessionService = new SessionService(sessionRepository, userRepository);

        Assertions.assertThrows(NotFoundException.class, () -> {
            sessionService.participate(1L, 2L);
        });

    }

    @Test
    void participateBadRequest() {

        Session session = Session.builder().description("description").date(new Date()).name("toto").id(1L).users(new ArrayList<>()).build();
        when(sessionRepository.findById(1L)).thenReturn(Optional.of(session));

        User user = User.builder().id(2L).email("toto.com").firstName("toto").lastName("tata").admin(false).password("").build();
        when(userRepository.findById(2L)).thenReturn(Optional.of(user));

        session.getUsers().add(user);

        SessionService sessionService = new SessionService(sessionRepository, userRepository);

        Assertions.assertThrows(BadRequestException.class, () -> {
            sessionService.participate(1L, 2L);
        });

    }



    @Test
    void noLongerParticipate() {

        Session session = Session.builder().description("description").date(new Date()).name("name").id(1L).users(new ArrayList<>()).build();
        when(sessionRepository.findById(1L)).thenReturn(Optional.of(session));

        User user = User.builder().id(2L).email("toto.com").firstName("toto").lastName("tata").admin(false).password("").build();
        when(userRepository.findById(2L)).thenReturn(Optional.of(user));

        SessionService sessionService = new SessionService(sessionRepository, userRepository);
        sessionService.participate(session.getId(), user.getId());
        sessionService.noLongerParticipate(session.getId(), user.getId());
        assertThat(session.getUsers()).hasSize(0);


    }
}