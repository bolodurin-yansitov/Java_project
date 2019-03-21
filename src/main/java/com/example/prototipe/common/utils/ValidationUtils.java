package com.example.prototipe.common.utils;

public class ValidationUtils {
    public static void validateIsNotNull(Object object, String message)
            throws ValidationExseption{
        if(object != null)
            throw new ValidationExseption(message);
    }

    public static void validateIsNull(Object object, String message)
            throws ValidationExseption{
        if(object == null)
            throw new ValidationExseption(message);
    }
}
