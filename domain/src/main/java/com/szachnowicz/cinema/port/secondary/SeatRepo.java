package com.szachnowicz.cinema.port.secondary;

import java.util.List;

public interface SeatRepo {

    void validateSeatCodes(List<String> seatCodes, Long movieHallId);

    List<String> getAvailableSeatCodes(Long movieOfferId);
}
