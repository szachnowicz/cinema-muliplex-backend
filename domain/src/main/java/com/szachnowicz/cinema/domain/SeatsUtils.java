package com.szachnowicz.cinema.domain;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;


class SeatsUtils {
     static boolean areSeatsCodesAdjacent(List<String> seats) {
        if (seats.size() == 1) {
            return true;
        }

        @AllArgsConstructor
        class SeatCode {
            char letter;
            int seatNo;
        }
        List<SeatCode> seatCodes = seats
                .stream()
                .sorted()
                .map(String::toLowerCase)
                .map(c -> new SeatCode(c.charAt(0), Integer.valueOf(c.substring(1))))
                .collect(Collectors.toList());

        boolean areAdjacent = false;
        for (int i = 1; i <= seatCodes.size() - 1; i++) {
            SeatCode a = seatCodes.get(i - 1);
            SeatCode b = seatCodes.get(i);
            areAdjacent = a.letter == b.letter &&
                    (a.seatNo == b.seatNo - 1 || a.seatNo == b.seatNo + 1);
        }
        return areAdjacent;


    }
}
