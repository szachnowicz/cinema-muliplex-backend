package com.szachnowicz.cinema.port.secondary;

import com.szachnowicz.cinema.dto.ReservationDto;

import java.util.List;

public interface ReservationRepository {
    String saveReservation(ReservationDto reservationDto);

    void validateIfSeatNotReserved(List<String> seatCodes, Long cinemaHallId);


    boolean confirmReservation(String code);
}
