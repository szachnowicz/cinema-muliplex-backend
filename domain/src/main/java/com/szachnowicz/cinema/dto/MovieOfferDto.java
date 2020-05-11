package com.szachnowicz.cinema.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class MovieOfferDto {
    private Long movieOfferId;
    private LocalDateTime screeningTime;
    private String movieName;
    private String movieDetails;
    private Long cinemaHallId;
    private List<String> availableSeatCodes;

}
