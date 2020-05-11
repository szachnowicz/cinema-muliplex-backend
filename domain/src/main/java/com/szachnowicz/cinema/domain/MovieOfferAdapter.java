package com.szachnowicz.cinema.domain;

import com.szachnowicz.cinema.dto.MovieOfferDto;
import com.szachnowicz.cinema.dto.MovieOfferSimplyDto;
import com.szachnowicz.cinema.port.primary.MovieOfferPort;
import com.szachnowicz.cinema.port.secondary.MovieOfferRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
class MovieOfferAdapter implements MovieOfferPort {

    private final MovieOfferRepo movieOfferRepo;

    @Override
    public MovieOfferDto getMovieOffer(Long movieOfferId) {
        return movieOfferRepo.getMovieById(movieOfferId);
    }

    @Override
    public Collection<MovieOfferSimplyDto> getAllMovieOffersInDateRage(LocalDateTime begin, LocalDateTime end) {
        return movieOfferRepo.getAllMovieOffersInDateRage(begin, end).stream()
                .map(this::toSimplyDto)
                .collect(Collectors.toList());
    }

    private MovieOfferSimplyDto toSimplyDto(MovieOfferDto dto) {
        return new MovieOfferSimplyDto(dto.getMovieOfferId(), dto.getScreeningTime(), dto.getMovieName());
    }

}
