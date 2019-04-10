package com.example.prototipe.common.utils;

public class ValidationUtils {
    public static void validateIsNotNull(Object object, String message)
            throws ValidationException {
        if(object != null)
            throw new ValidationException(message);
    }

    public static void validateIsNull(Object object, String message)
            throws ValidationException {
        if(object == null)
            throw new ValidationException(message);
    }
}
