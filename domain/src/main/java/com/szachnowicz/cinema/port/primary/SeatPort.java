package com.szachnowicz.cinema.port.primary;

import java.util.List;

public interface SeatPort {

    List<String> getAvailableSeatCodes();

    boolean areSeatsCodesAdjacent(List<String> seats);

}
