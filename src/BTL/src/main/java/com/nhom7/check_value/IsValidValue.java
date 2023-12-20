package com.nhom7.check_value;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class IsValidValue {
    public boolean isValidLocalDate(String value) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate.parse(value, formatter);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public boolean isValidLocaTime(String value) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            LocalTime.parse(value, formatter);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
