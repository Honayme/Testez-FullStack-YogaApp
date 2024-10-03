package com.openclassrooms.starterjwt.mapper;

import com.openclassrooms.starterjwt.dto.TeacherDto;
import com.openclassrooms.starterjwt.models.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class TeacherMapperTest {

    @Test
    void toEntity() {

        TeacherDto teacherDto = new TeacherDto();
        teacherDto.setId(1L);
        teacherDto.setFirstName("John");
        teacherDto.setLastName("Doe");

        TeacherMapper teacherMapper = new TeacherMapperImpl();
        Teacher teacher = teacherMapper.toEntity(teacherDto);
        assertEquals(teacherDto.getId(), teacher.getId());
        assertEquals(teacherDto.getFirstName(), teacher.getFirstName());
        assertEquals(teacherDto.getLastName(), teacher.getLastName());

    }

    @Test
    void toDto() {
        Teacher teacher = new Teacher();
        teacher.setId(1L);
        teacher.setFirstName("John");
        teacher.setLastName("Doe");

        TeacherMapper teacherMapper = new TeacherMapperImpl();
        TeacherDto teacherDto = teacherMapper.toDto(teacher);
        assertEquals(teacher.getId(), teacherDto.getId());
        assertEquals(teacher.getFirstName(), teacherDto.getFirstName());
        assertEquals(teacher.getLastName(), teacherDto.getLastName());
    }

    @Test
    void toDtoList() {
        Teacher teacher = new Teacher();
        teacher.setId(1L);
        teacher.setFirstName("John");
        teacher.setLastName("Doe");

        Teacher teacher2 = new Teacher();
        teacher2.setId(2L);
        teacher2.setFirstName("Jane");
        teacher2.setLastName("Doe");

        List<Teacher> teachers = new ArrayList<>();
        teachers.add(teacher);
        teachers.add(teacher2);

        TeacherMapper teacherMapper = new TeacherMapperImpl();
        List<TeacherDto> teacherDtos = teacherMapper.toDto(teachers);
        assertEquals(teachers.size(), teacherDtos.size());
        assertEquals(teachers.get(0).getId(), teacherDtos.get(0).getId());
        assertEquals(teachers.get(0).getFirstName(), teacherDtos.get(0).getFirstName());
        assertEquals(teachers.get(0).getLastName(), teacherDtos.get(0).getLastName());

    }

    @Test
    void toEntityList() {
        TeacherDto teacherDto = new TeacherDto();
        teacherDto.setId(1L);
        teacherDto.setFirstName("John");
        teacherDto.setLastName("Doe");

        TeacherDto teacherDto2 = new TeacherDto();
        teacherDto2.setId(2L);
        teacherDto2.setFirstName("Jane");
        teacherDto2.setLastName("Doe");

        List<TeacherDto> teacherDtos = new ArrayList<>();
        teacherDtos.add(teacherDto);
        teacherDtos.add(teacherDto2);

        TeacherMapper teacherMapper = new TeacherMapperImpl();
        List<Teacher> teachers = teacherMapper.toEntity(teacherDtos);
        assertEquals(teacherDtos.size(), teachers.size());
        assertEquals(teacherDtos.get(0).getId(), teachers.get(0).getId());
        assertEquals(teacherDtos.get(0).getFirstName(), teachers.get(0).getFirstName());
        assertEquals(teacherDtos.get(0).getLastName(), teachers.get(0).getLastName());
    }

}