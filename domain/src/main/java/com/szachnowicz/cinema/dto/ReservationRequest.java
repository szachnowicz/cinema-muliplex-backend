package com.szachnowicz.cinema.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class ReservationRequest {
    private Long movieId;
    private List<ReservedSeatDto> seatDto;
    private String name;
    private String lastName;

}
