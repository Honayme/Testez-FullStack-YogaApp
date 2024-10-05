package com.openclassrooms.starterjwt.exception;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class BadRequestExceptionTest {

    @Test
    void testConstructor() {
        BadRequestException badRequestException = new BadRequestException();
        assertNotNull(badRequestException);
    }

}