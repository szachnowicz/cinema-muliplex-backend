package com.szachnowicz.cinema.contoller.exceptions;


import com.szachnowicz.cinema.dto.error.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDTO {
    private String errorCode;
    private String description;


    public ErrorDTO(ErrorCode errorCode, String message) {
        this.errorCode = errorCode.name();
        this.description = message;
    }
}


