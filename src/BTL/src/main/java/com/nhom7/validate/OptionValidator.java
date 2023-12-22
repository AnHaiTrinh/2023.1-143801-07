package com.nhom7.validate;

import java.util.List;

public class OptionValidator {
    public static <T> boolean isValidOption(T value, List<T> options) {
        return options.contains(value);
    }
}
