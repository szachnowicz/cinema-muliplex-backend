package com.szachnowicz.cinema.contoller;

import com.szachnowicz.cinema.dto.error.BusinessException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static com.szachnowicz.cinema.contoller.exceptions.CommonErrorCodes.WRONG_DATE_FORMAT;

class DateUtil {

    private static final String PATTERN = "yyyy-MM-dd HH:mm";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN);


    public static LocalDateTime parse(String date) {
        try {
            // simply way to validate the date format
            new SimpleDateFormat(PATTERN).parse(date);
            return LocalDateTime.parse(date, formatter);
        } catch (ParseException | DateTimeParseException e) {
            e.printStackTrace();
            throw new BusinessException(WRONG_DATE_FORMAT, "Wrong date format - please follow this one yyyy-mm-dd hh:mm");
        }

    }
}
