package com.szachnowicz.cinema.domain;

import com.szachnowicz.cinema.dto.ReservationDto;
import com.szachnowicz.cinema.dto.ReservedSeatDto;
import com.szachnowicz.cinema.dto.error.BusinessException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static com.szachnowicz.cinema.dto.error.BusinessErrorCodes.RESERVATION_TIME_LIMIT;


class Reservation {
    Reservation() {
        this.reservationTime = LocalDateTime.now();
    }

    private LocalDateTime reservationTime;
    private Long movieOfferId;
    private List<ReservedSeatDto> seats;
    private BigDecimal price = BigDecimal.ZERO;
    private String name;
    private String lastName;


    Reservation reservationName(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
        return this;
    }

    Reservation movieId(Long movieOfferId) {
        this.movieOfferId = movieOfferId;
        return this;
    }

    Reservation addSeats(List<ReservedSeatDto> reservedSeats) {
        this.seats = reservedSeats;
        for (ReservedSeatDto s : seats) {
            this.price = price.add(s.getTicketType().getPrice());
        }
        return this;
    }

    Reservation checkReservationTimeLimitation(LocalDateTime screeningTime) {
        long difference = ChronoUnit.MINUTES.between(reservationTime, screeningTime);
        if (difference <= 15) {
            throw new BusinessException(RESERVATION_TIME_LIMIT,
                    "Reservation can by only made 15 min before screening time");
        }
        return this;
    }


    ReservationDto toDto() {
        return ReservationDto.builder()
                .movieOfferId(movieOfferId)
                .price(price)
                .seats(seats)
                .reservationTime(reservationTime)
                .name(name)
                .lastName(lastName)
                .build();
    }
}
