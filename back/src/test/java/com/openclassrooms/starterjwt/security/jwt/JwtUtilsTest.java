package com.openclassrooms.starterjwt.security.jwt;

import com.openclassrooms.starterjwt.security.services.UserDetailsImpl;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.context.ActiveProfiles;
import java.util.Date;
import com.openclassrooms.starterjwt.security.jwt.JwtUtils;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class JwtUtilsTest {

    @InjectMocks
    private JwtUtils jwtUtils;


    @BeforeEach
    void setUp() {
        jwtUtils = new JwtUtils();
        ReflectionTestUtils.setField(jwtUtils, "jwtSecret", "testSecret");
        ReflectionTestUtils.setField(jwtUtils, "jwtExpirationMs", 3_600_000); // 1 hour
    }



    @Test
    void generateJwtToken() {

        Authentication authentication = mock(Authentication.class);
        when(authentication.getPrincipal()).thenReturn(new UserDetailsImpl(1L, "toto", "tata", "titi",  true, "password"));

        String jwtToken = jwtUtils.generateJwtToken(authentication);

        assertNotNull(jwtToken);
        assertTrue(jwtUtils.validateJwtToken(jwtToken));

    }

    @Test
    void getUserNameFromJwtToken() {

        UserDetailsImpl userDetails = UserDetailsImpl.builder().id(1L).username("toto").firstName("tata").lastName("titi").admin(true).password("password").build();

        String jwtToken = Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3_600_000)) // 1 hour
                .signWith(SignatureAlgorithm.HS512, "testSecret")
                .compact();



        String username = jwtUtils.getUserNameFromJwtToken(jwtToken);
        assertEquals(userDetails.getUsername(), username);

    }

    @Test
    void validateJwtToken() {

            UserDetailsImpl userDetails = UserDetailsImpl.builder().id(1L).username("toto").firstName("tata").lastName("titi").admin(true).password("password").build();

            String jwtToken = Jwts.builder()
                    .setSubject(userDetails.getUsername())
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + 3_600_000)) // 1 hour
                    .signWith(SignatureAlgorithm.HS512, "testSecret")
                    .compact();

            assertTrue(jwtUtils.validateJwtToken(jwtToken));
    }
}