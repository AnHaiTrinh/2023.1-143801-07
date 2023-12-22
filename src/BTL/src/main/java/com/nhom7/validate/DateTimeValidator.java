package com.nhom7.validate;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTimeValidator {
    public static boolean isValidLocalDate(String value, DateTimeFormatter formatter) {
        try {
            LocalDate.parse(value, formatter);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public static boolean isValidLocalTime(String value, DateTimeFormatter formatter) {
        try {
            LocalTime.parse(value, formatter);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
