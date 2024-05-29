package com.avmathman.elasticurl.domain.BaseConverter;

import com.avmathman.elasticurl.domain.BaseConverter.exception.BaseConverterException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Base64ConverterServiceTest {

    private Base64ConverterService service;

    @BeforeEach
    public void setUp() {
        service = new Base64ConverterService();
    }

    @Test
    public void encode_convertsToBase64String_validateBase64String() {

        // Assign
        long value = 123L;
        String expected = "1z";

        // Act
        String result = service.encode(value);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result).isBase64();
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void encode_convertsToBase64String_validateNotEmptyString() {

        // Assign
        long value = 123L;
        String expected = "1z";

        // Act
        String result = service.encode(value);

        // Assert
        assertThat(result).isNotEmpty();
    }

    @Test
    public void decode_convertsToLong_validateValue() {

        // Assign
        String value = "1z";
        long expected = 123L;

        // Act
        long result = service.decode(value);

        // Assert
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void decode_convertsToLong_validateInvalidValue() {

        // Assign
        String value = "1z";

        // Act
        long result = service.decode(value);

        // Assert
        assertThat(result).isNotEqualTo(1L);
    }

    @Test
    public void decode_passedNullValue_throwBaseConverterException() {

        // Assign
        String value = null;

        // Act
        BaseConverterException thrown = assertThrows(
                BaseConverterException.class,
                () -> service.decode(value),
                "Expected doThing() to throw, but it didn't"
        );

        // Assert
        assertTrue(thrown.getMessage().contains("Value cannot be null"));
    }

}
