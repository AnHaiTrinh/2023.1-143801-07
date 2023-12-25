package com.nhom7.validate;

import com.nhom7.config.Settings;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class DateTimeValidatorTest {

    @ParameterizedTest
    @CsvSource({
            "2021-01-01, true",
            "2021-01-32, false",
            "2023-02-29, false",
            "2021-02-28, true",
            "31/03/2022, false",
            "12/24/2023, false",
            "2021-1-2, false",
    })
    void isValidLocalDate(String value, String expected) {
        Boolean isValid = Boolean.parseBoolean(expected);
        assertEquals(isValid, DateTimeValidator.isValidLocalDate(value, Settings.DATE_FORMATTER));
    }

    @ParameterizedTest
    @CsvSource({
            "00:00:00, true",
            "7h, false",
            "12:00:00, true",
            "12:00, false",
            "12:00:60, false",
            "12:60:00, false",
            "12:00:00.000, false",
            "45:00:00, false",
            "99:99:99, false",
            "17.03.49, false"
    })
    void isValidLocalTime(String value, String expected) {
        Boolean isValid = Boolean.parseBoolean(expected);
        assertEquals(isValid, DateTimeValidator.isValidLocalTime(value, Settings.TIME_FORMATTER));
    }
}