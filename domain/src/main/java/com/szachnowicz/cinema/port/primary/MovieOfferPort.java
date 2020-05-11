package com.szachnowicz.cinema.port.primary;


import com.szachnowicz.cinema.dto.MovieOfferDto;
import com.szachnowicz.cinema.dto.MovieOfferSimplyDto;

import java.time.LocalDateTime;
import java.util.Collection;


public interface MovieOfferPort {

    MovieOfferDto getMovieOffer(Long movieOfferId);

    Collection<MovieOfferSimplyDto> getAllMovieOffersInDateRage(LocalDateTime begin, LocalDateTime end);


}
