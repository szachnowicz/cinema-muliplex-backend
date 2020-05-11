package com.szachnowicz.cinema.contoller.exceptions;

import com.szachnowicz.cinema.dto.error.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class RestExceptionHandler {


    public @ResponseBody
    @ExceptionHandler(BusinessException.class)
    ErrorDTO handlException(BusinessException exception) {
        log.info("Business Exception encountered {}, with message", exception.getErrorCode(), exception.getMessage());
        log.debug("Business error:", exception);
        return new ErrorDTO(exception.getErrorCode(), exception.getMessage());
    }

}