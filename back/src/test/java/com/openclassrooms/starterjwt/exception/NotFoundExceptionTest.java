package com.openclassrooms.starterjwt.exception;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class NotFoundExceptionTest {

    @Test
    void testConstructor() {
        NotFoundException notFoundException = new NotFoundException();
        assertNotNull(notFoundException);
    }


}