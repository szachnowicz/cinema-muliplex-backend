package com.szachnowicz.cinema.port.secondary;

import com.szachnowicz.cinema.dto.MovieOfferDto;

import java.time.LocalDateTime;
import java.util.Collection;

public interface MovieOfferRepo {

    MovieOfferDto getMovieById(Long movieId);

    Collection<MovieOfferDto> getAllMovieOffersInDateRage(LocalDateTime from, LocalDateTime to);

}
