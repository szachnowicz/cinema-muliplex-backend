package com.szachnowicz.cinema.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieOfferSimplyDto {
    private Long movieOfferId;
    private LocalDateTime screeningTime;
    private String movieName;

}
