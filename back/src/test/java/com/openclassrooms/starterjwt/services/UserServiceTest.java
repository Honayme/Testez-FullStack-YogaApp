package com.openclassrooms.starterjwt.services;

import com.openclassrooms.starterjwt.models.Session;
import com.openclassrooms.starterjwt.models.User;
import com.openclassrooms.starterjwt.repository.SessionRepository;
import com.openclassrooms.starterjwt.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class UserServiceTest {
    @Mock
    private UserRepository userRepository;



    @Test
    void delete() {
        User user = new User();
        user.setId(1L);
        doNothing().when(userRepository).deleteById(1L);
        UserService userService = new UserService(userRepository);
        userService.delete(1L);
        verify(userRepository, times(1)).deleteById(1L);
    }


    @Test
    void findById() {
        User user = new User();
        user.setId(1L);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        UserService userService = new UserService(userRepository);
        User foundUser = userService.findById(1L);
        assertThat(foundUser.getId()).isEqualTo(1L);
    }

    @Test
    void findByBadId() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());
        UserService userService = new UserService(userRepository);
        User foundUser = userService.findById(1L);
        assertThat(foundUser).isNull();
    }

}