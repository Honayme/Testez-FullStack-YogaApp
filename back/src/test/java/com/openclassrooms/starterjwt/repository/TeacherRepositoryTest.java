package com.openclassrooms.starterjwt.repository;

import com.openclassrooms.starterjwt.models.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class TeacherRepositoryTest {

    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    void testSaveTeacher() {
        // Arrange: Crée un nouvel enseignant
        Teacher teacher = Teacher.builder()
                .firstName("John")
                .lastName("Doe")
                .build();

        // Act: Sauvegarde l'enseignant
        Teacher savedTeacher = teacherRepository.save(teacher);

        // Assert: Vérifie que l'enseignant est bien sauvegardé et qu'il a un ID généré
        assertThat(savedTeacher.getId()).isNotNull();
        assertThat(savedTeacher.getFirstName()).isEqualTo("John");
        assertThat(savedTeacher.getLastName()).isEqualTo("Doe");
    }
}
