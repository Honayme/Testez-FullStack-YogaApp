package com.openclassrooms.starterjwt.repository;

import com.openclassrooms.starterjwt.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test") // Activer le profil de test si nécessaire
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void testFindByEmail() {
        // Arrange: créer et sauvegarder un utilisateur
        User user = new User();
        user.setEmail("test@example.com");
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setPassword("password123");
        userRepository.save(user);

        // Act: utiliser la méthode findByEmail
        Optional<User> foundUser = userRepository.findByEmail("test@example.com");

        // Assert: vérifier que l'utilisateur a bien été trouvé
        assertThat(foundUser).isPresent();
        assertThat(foundUser.get().getEmail()).isEqualTo("test@example.com");
        assertThat(foundUser.get().getFirstName()).isEqualTo("John");
        assertThat(foundUser.get().getLastName()).isEqualTo("Doe");
    }

    @Test
    void testFindByEmail_NotFound() {
        // Act: utiliser la méthode findByEmail avec un email qui n'existe pas
        Optional<User> foundUser = userRepository.findByEmail("nonexistent@example.com");

        // Assert: vérifier que l'utilisateur n'a pas été trouvé
        assertThat(foundUser).isEmpty();
    }

    @Test
    void testExistsByEmail() {
        // Arrange: créer et sauvegarder un utilisateur
        User user = new User();
        user.setEmail("exists@example.com");
        user.setFirstName("Jane");
        user.setLastName("Doe");
        user.setPassword("password123");
        userRepository.save(user);

        // Act: utiliser la méthode existsByEmail
        Boolean exists = userRepository.existsByEmail("exists@example.com");

        // Assert: vérifier que l'email existe
        assertThat(exists).isTrue();
    }

    @Test
    void testExistsByEmail_NotFound() {
        // Act: utiliser la méthode existsByEmail avec un email qui n'existe pas
        Boolean exists = userRepository.existsByEmail("doesnotexist@example.com");

        // Assert: vérifier que l'email n'existe pas
        assertThat(exists).isFalse();
    }
}
