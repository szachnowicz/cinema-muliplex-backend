package com.szachnowicz.cinema.dto.error;

public enum BusinessErrorCodes implements ErrorCode {
    RESERVATION_TIME_LIMIT,
    WRONG_DATE_FORMAT,
    MOVIE_ID_NOT_EXIST,
    SEAT_NOT_EXIST,
    ALREADY_RESERVED,
    SEAT_NOT_ADJACENT
}
