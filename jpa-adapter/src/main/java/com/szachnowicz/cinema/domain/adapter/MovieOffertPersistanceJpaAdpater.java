package com.szachnowicz.cinema.domain.adapter;

import com.szachnowicz.cinema.domain.mappers.MovieOfferMapper;
import com.szachnowicz.cinema.domain.repo.MovieOfferJpaRepository;
import com.szachnowicz.cinema.dto.MovieOfferDto;
import com.szachnowicz.cinema.port.secondary.MovieOfferRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
class MovieOffertPersistanceJpaAdpater implements MovieOfferRepo {


    private final MovieOfferJpaRepository movieOfferJpaRepository;
    private final MovieOfferMapper movieOfferMapper;


    @Override
    public MovieOfferDto getMovieById(Long movieId) {
        return movieOfferMapper.toDto(movieOfferJpaRepository.getOne(movieId));
    }

    @Override
    public Collection<MovieOfferDto> getAllMovieOffersInDateRage(LocalDateTime from, LocalDateTime to) {

        Comparator<MovieOfferDto> compareByName = Comparator
                .comparing(MovieOfferDto::getMovieName)
                .thenComparing(MovieOfferDto::getScreeningTime);

        return movieOfferMapper.toList(movieOfferJpaRepository.findAllByScreeningTimeBetween(from, to))
                .stream().sorted(compareByName).collect(Collectors.toList());

    }
}