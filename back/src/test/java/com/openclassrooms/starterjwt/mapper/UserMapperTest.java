package com.openclassrooms.starterjwt.mapper;

import com.openclassrooms.starterjwt.dto.UserDto;
import com.openclassrooms.starterjwt.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@ActiveProfiles("test")
class UserMapperTest {


    @Test
    void toEntity() {
        UserDto userDto = new UserDto();
        userDto.setId(1L);
        userDto.setPassword("password");
        userDto.setEmail("email");
        userDto.setFirstName("toto");
        userDto.setLastName("tata");
        userDto.setCreatedAt(LocalDateTime.now());

        UserMapper userMapper = new UserMapperImpl();
        User user = userMapper.toEntity(userDto);
        assertEquals(userDto.getId(), user.getId());
        assertEquals(userDto.getPassword(), user.getPassword());
        assertEquals(userDto.getEmail(), user.getEmail());
        assertEquals(userDto.getFirstName(), user.getFirstName());
        assertEquals(userDto.getLastName(), user.getLastName());
        assertEquals(userDto.getCreatedAt(), user.getCreatedAt());

    }

    @Test
    void toDto() {
        User user = new User();
        user.setId(1L);
        user.setPassword("password");
        user.setEmail("email");
        user.setFirstName("toto");
        user.setLastName("tata");
        user.setCreatedAt(LocalDateTime.now());

        UserMapper userMapper = new UserMapperImpl();
        UserDto userDto = userMapper.toDto(user);
        assertEquals(user.getId(), userDto.getId());
        assertEquals(user.getPassword(), userDto.getPassword());
        assertEquals(user.getEmail(), userDto.getEmail());
        assertEquals(user.getFirstName(), userDto.getFirstName());
        assertEquals(user.getLastName(), userDto.getLastName());
        assertEquals(user.getCreatedAt(), userDto.getCreatedAt());
    }

    @Test
    void toEntityList() {

        UserDto userDto = new UserDto();
        userDto.setId(1L);
        userDto.setPassword("password");
        userDto.setEmail("email");
        userDto.setFirstName("toto");
        userDto.setLastName("tata");
        userDto.setCreatedAt(LocalDateTime.now());

        UserDto userdto2 = new UserDto();
        userdto2.setId(1L);
        userdto2.setPassword("password");
        userdto2.setEmail("email");
        userdto2.setFirstName("toto");
        userdto2.setLastName("tata");
        userdto2.setCreatedAt(LocalDateTime.now());

        List<UserDto> userDtos = new ArrayList<>();
        userDtos.add(userDto);
        userDtos.add(userdto2);

UserMapper userMapper = new UserMapperImpl();
        List<User> users = userMapper.toEntity(userDtos);
        assertEquals(userDtos.size(), users.size());
        assertEquals(userDtos.get(0).getId(), users.get(0).getId());
        assertEquals(userDtos.get(0).getPassword(), users.get(0).getPassword());
        assertEquals(userDtos.get(0).getEmail(), users.get(0).getEmail());
        assertEquals(userDtos.get(0).getFirstName(), users.get(0).getFirstName());
        assertEquals(userDtos.get(0).getLastName(), users.get(0).getLastName());
        assertEquals(userDtos.get(0).getCreatedAt(), users.get(0).getCreatedAt());

    }

    @Test
    void toDtoList() {
        User user = new User();
        user.setId(1L);
        user.setPassword("password");
        user.setEmail("email");
        user.setFirstName("toto");
        user.setLastName("tata");
        user.setCreatedAt(LocalDateTime.now());

        User user2 = new User();
        user2.setId(1L);
        user2.setPassword("password");
        user2.setEmail("email");
        user2.setFirstName("toto");
        user2.setLastName("tata");
        user2.setCreatedAt(LocalDateTime.now());

        List<User> users = new ArrayList<>();
        users.add(user);
        users.add(user2);

        UserMapper userMapper = new UserMapperImpl();
        List<UserDto> userDtos = userMapper.toDto(users);
        assertEquals(users.size(), userDtos.size());
        assertEquals(users.get(0).getId(), userDtos.get(0).getId());
        assertEquals(users.get(0).getPassword(), userDtos.get(0).getPassword());
        assertEquals(users.get(0).getEmail(), userDtos.get(0).getEmail());
        assertEquals(users.get(0).getFirstName(), userDtos.get(0).getFirstName());
        assertEquals(users.get(0).getLastName(), userDtos.get(0).getLastName());
        assertEquals(users.get(0).getCreatedAt(), userDtos.get(0).getCreatedAt());
    }

}