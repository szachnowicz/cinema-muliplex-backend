package com.szachnowicz.cinema.dto;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public enum TicketType {
    ADULT(BigDecimal.valueOf(25.00), "PLN"),
    STUDENT(BigDecimal.valueOf(18.00), "PLN"),
    CHILD(BigDecimal.valueOf(12.50), "PLN");


    TicketType(BigDecimal price, String currnecyCode) {
        this.price = price;
        this.currencyCode = currnecyCode;
    }


    private BigDecimal price;
    private String currencyCode;
}
