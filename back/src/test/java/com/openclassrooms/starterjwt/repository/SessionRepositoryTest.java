package com.openclassrooms.starterjwt.repository;

import com.openclassrooms.starterjwt.models.Session;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test") // Activer le profil de test si nécessaire
class SessionRepositoryTest {

    @Autowired
    private SessionRepository sessionRepository;

    @Test
    void testSaveSession() {
        // Arrange: créer et sauvegarder une session
        Session session = new Session();
        session.setName("Yoga for Beginners");
        session.setDescription("A gentle introduction to yoga for beginners.");
        session.setDate(new Date());

        // Act: sauvegarder la session
        Session savedSession = sessionRepository.save(session);

        // Assert: vérifier que la session a bien été sauvegardée avec un ID généré
        assertThat(savedSession.getId()).isNotNull();
        assertThat(savedSession.getName()).isEqualTo("Yoga for Beginners");
    }

    @Test
    void testFindById() {
        // Arrange: créer et sauvegarder une session
        Session session = new Session();
        session.setName("Advanced Vinyasa Flow");
        session.setDescription("A dynamic and challenging vinyasa yoga session for advanced practitioners.");
        session.setDate(new Date());
        Session savedSession = sessionRepository.save(session);

        // Act: chercher la session par son ID
        Optional<Session> foundSession = sessionRepository.findById(savedSession.getId());

        // Assert: vérifier que la session a bien été trouvée
        assertThat(foundSession).isPresent();
        assertThat(foundSession.get().getName()).isEqualTo("Advanced Vinyasa Flow");
    }

    @Test
    void testFindAll() {
        // Arrange: créer et sauvegarder plusieurs sessions de yoga
        Session session1 = new Session();
        session1.setName("Morning Yoga");
        session1.setDescription("Start your day with a gentle morning yoga session.");
        session1.setDate(new Date());

        Session session2 = new Session();
        session2.setName("Restorative Yoga");
        session2.setDescription("A slow, relaxing yoga session to release tension and stress.");
        session2.setDate(new Date());

        sessionRepository.save(session1);
        sessionRepository.save(session2);

        // Act: récupérer toutes les sessions
        List<Session> sessions = sessionRepository.findAll();

        // Assert: vérifier que seules les sessions créées dans ce test sont présentes
        assertThat(sessions.stream().filter(s -> s.getName().equals("Morning Yoga") || s.getName().equals("Restorative Yoga")))
                .hasSize(2);
    }

    @Test
    void testDeleteById() {
        // Arrange: créer et sauvegarder une session
        Session session = new Session();
        session.setName("Evening Yin Yoga");
        session.setDescription("A calm and relaxing Yin Yoga session to end the day.");
        session.setDate(new Date());
        Session savedSession = sessionRepository.save(session);

        // Act: supprimer la session par son ID
        sessionRepository.deleteById(savedSession.getId());

        // Assert: vérifier que la session a bien été supprimée
        Optional<Session> deletedSession = sessionRepository.findById(savedSession.getId());
        assertThat(deletedSession).isEmpty();
    }
}
