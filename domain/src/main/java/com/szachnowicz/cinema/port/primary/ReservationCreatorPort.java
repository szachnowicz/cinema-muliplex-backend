package com.szachnowicz.cinema.port.primary;

import com.szachnowicz.cinema.dto.ReservationRequest;
import com.szachnowicz.cinema.dto.ReservationResponse;

public interface ReservationCreatorPort {

    ReservationResponse createReservation(ReservationRequest reservationRequest);

    String confirmReservation(String code);

}
