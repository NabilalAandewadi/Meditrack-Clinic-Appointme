package com.airtribe.meditrack.util;



import com.airtribe.meditrack.exception.InvalidDataException;

import java.util.Date;

public class DateUtil {
    public static void validateDate(Date date) throws InvalidDataException {
        if (date.before(new Date())) {
            throw new InvalidDataException("Date cannot be in the past");
        }
    }
}
