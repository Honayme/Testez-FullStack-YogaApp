package com.openclassrooms.starterjwt.services;

import com.openclassrooms.starterjwt.models.Teacher;
import com.openclassrooms.starterjwt.repository.TeacherRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class TeacherServiceTest {

    @Mock
    private TeacherService teacherService;

    @Mock
    private TeacherRepository teacherRepository;

    @Mock
    private Teacher teacher;

    @Test
    void findAll() {
        List<Teacher> teachers = new ArrayList<>();
        Teacher teacher = new Teacher();
        teacher.setId(1L);
        teachers.add(teacher);
        when(teacherRepository.findAll()).thenReturn(teachers);
        TeacherService teacherService = new TeacherService(teacherRepository);
        List<Teacher> foundTeachers = teacherService.findAll();
        assertThat(foundTeachers.size()).isEqualTo(1);

    }

    @Test
    void findById() {
        Teacher teacher = new Teacher();
        teacher.setId(1L);

        when(teacherRepository.findById(1L)).thenReturn(Optional.of(teacher));
        TeacherService teacherService = new TeacherService(teacherRepository);
        Teacher foundTeacher = teacherService.findById(1L);
        assertThat(foundTeacher.getId()).isEqualTo(1L);
    }
}