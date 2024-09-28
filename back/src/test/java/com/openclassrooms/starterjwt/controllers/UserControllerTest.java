package com.openclassrooms.starterjwt.controllers;

import com.openclassrooms.starterjwt.dto.UserDto;
import com.openclassrooms.starterjwt.mapper.UserMapper;
import com.openclassrooms.starterjwt.models.User;
import com.openclassrooms.starterjwt.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerTest {

    @Mock
    private UserController userController;

    @Mock
    private UserMapper userMapper;

    @Mock
    private UserService userService;

    @Autowired
    MockMvc mockMvc;

    private User user;
    private UserDto userDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Initialisation commune pour plusieurs tests
        Long id = 1L;
        String lastName = "toto";
        String firstName = "tata";

        user = new User();
        user.setId(id);
        user.setLastName(lastName);
        user.setFirstName(firstName);

        userDto = new UserDto();
        userDto.setId(id);
        userDto.setLastName(lastName);
        userDto.setFirstName(firstName);

        // Mock du mapper
        when(userMapper.toDto(user)).thenReturn(userDto);
    }

    @Test
    void findById_should_return_user() {
        // Mock du service
        when(userService.findById(1L)).thenReturn(user);

        ResponseEntity<?> responseEntity = userController.findById("1");

        // Vérifications
        assertEquals(userDto, responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void findById_should_return_not_found() {
        // Mock d'un user non trouvé
        when(userService.findById(1L)).thenReturn(null);

        ResponseEntity<?> responseEntity = userController.findById("1");

        // Vérification du statut NOT_FOUND
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void delete_should_return_ok() {
        // Mock du service pour un user trouvé
        when(userService.findById(1L)).thenReturn(user);

        // Mock de l'utilisateur courant
        UserDetails userDetails = mock(UserDetails.class);
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userDetails, null));

        ResponseEntity<?> responseEntity = userController.save("1");

        // Vérification de la réponse OK
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void delete_should_return_not_found() {
        // Mock d'un user non trouvé
        when(userService.findById(1L)).thenReturn(null);

        ResponseEntity<?> responseEntity = userController.save("1");

        // Vérification du statut NOT_FOUND
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void delete_should_return_unauthorized() {
        // Mock du service pour un user trouvé
        when(userService.findById(1L)).thenReturn(user);

        // Mock de l'utilisateur courant avec un nom d'utilisateur incorrect
        UserDetails userDetails = mock(UserDetails.class);
        when(userDetails.getUsername()).thenReturn("toto.com");
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userDetails, null));

        ResponseEntity<?> responseEntity = userController.save("1");

        // Vérification du statut UNAUTHORIZED
        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
    }

    @Test
    @WithMockUser
    public void shouldFindById_withMockMvc() throws Exception {
        // Test d'intégration avec MockMvc pour vérifier l'API directement
        mockMvc.perform(MockMvcRequestBuilders.get("/api/user/1"))
                .andExpect(status().isOk());
    }
}
