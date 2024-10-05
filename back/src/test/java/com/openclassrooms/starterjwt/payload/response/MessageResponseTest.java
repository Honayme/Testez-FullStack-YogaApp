package com.openclassrooms.starterjwt.payload.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessageResponseTest {

    @Test
    void constructorAndGetters() {
        // Arrange
        String messageText = "Test Message";

        // Act
        MessageResponse messageResponse = new MessageResponse(messageText);

        // Assert
        assertEquals(messageText, messageResponse.getMessage());
    }

    @Test
    void setMessage() {
        // Arrange
        MessageResponse messageResponse = new MessageResponse("Original Message");

        // Act
        String newMessageText = "New Message";
        messageResponse.setMessage(newMessageText);

        // Assert
        assertEquals(newMessageText, messageResponse.getMessage());
    }

    @Test
    void setMessage_Null() {
        // Arrange
        MessageResponse messageResponse = new MessageResponse("Original Message");

        // Act
        messageResponse.setMessage(null);

        // Assert
        assertNull(messageResponse.getMessage());
    }

    @Test
    void setMessage_Empty() {
        // Arrange
        MessageResponse messageResponse = new MessageResponse("Original Message");

        // Act
        messageResponse.setMessage("");

        // Assert
        assertEquals("", messageResponse.getMessage());
    }
}