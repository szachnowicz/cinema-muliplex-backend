package com.szachnowicz.cinema.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class ReservationDto {
    private LocalDateTime reservationTime;
    private Long movieOfferId;
    private List<ReservedSeatDto> seats;
    private BigDecimal price;
    private String name;
    private String lastName;
}
