package com.nhom7.validate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class OptionValidatorTest {

    @Test
    void isValidOption() {
        assertTrue(OptionValidator.isValidOption("1", Arrays.asList("1", "2", "3")));
        assertFalse(OptionValidator.isValidOption(4, Arrays.asList(1, 2, 3)));
        assertFalse(OptionValidator.isValidOption(1, Arrays.asList("1", 2, "3", 4)));
        assertTrue(OptionValidator.isValidOption("CHECKIN", Arrays.asList("CHECKIN", "CHECKOUT")));
        assertFalse(OptionValidator.isValidOption("IN", Arrays.asList("CHECKIN", "CHECKOUT")));
    }
}