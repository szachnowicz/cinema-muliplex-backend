package com.szachnowicz.cinema.contoller.exceptions;

import com.szachnowicz.cinema.dto.error.ErrorCode;

public enum CommonErrorCodes implements ErrorCode {
    CONTENT_NOT_INIT,
    VALIDATION_ERROR,
    BAD_REQUEST,
    BUSINESS_EXCEPTION,
    NOT_FOUND,
    WRONG_DATE_FORMAT

}
