package com.openclassrooms.starterjwt.controllers;

import com.openclassrooms.starterjwt.dto.TeacherDto;
import com.openclassrooms.starterjwt.mapper.TeacherMapper;
import com.openclassrooms.starterjwt.models.Teacher;
import com.openclassrooms.starterjwt.services.TeacherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class TeacherControllerTest {

    @Mock
    private TeacherService teacherService;

    @Mock
    private TeacherMapper teacherMapper;

    @InjectMocks
    private TeacherController teacherController;

    private Teacher teacher;
    private TeacherDto teacherDto;
    private List<TeacherDto> teacherDtos;
    private List<Teacher> teachers;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        teacherController = new TeacherController(teacherService, teacherMapper);
        Long id = 1L;
        String lastName = "toto";
        String firstName = "tata";
        teacher = new Teacher();
        teacher.setId(id);
        teacher.setLastName(lastName);
        teacher.setFirstName(firstName);

        teacherDto = teacherMapper.toDto(teacher);
        teacherDtos = Collections.singletonList(teacherDto);
        teachers = Collections.singletonList(teacher);

    }

    @Test
    void findById() {
        when(teacherService.findById(1L)).thenReturn(teacher);
        when(teacherMapper.toDto(teacher)).thenReturn(teacherDto);
        ResponseEntity<?> response = teacherController.findById("1");
        assertEquals(teacherDto, response.getBody());

    }

    @Test
    void findAll() {
        when(teacherService.findAll()).thenReturn(teachers);
        when(teacherMapper.toDto(teachers)).thenReturn(teacherDtos);
        ResponseEntity<?> response = teacherController.findAll();
        assertEquals(teacherDtos, response.getBody());
    }
}