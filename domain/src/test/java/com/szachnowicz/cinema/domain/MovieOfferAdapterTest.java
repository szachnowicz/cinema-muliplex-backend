package com.szachnowicz.cinema.domain;

import com.szachnowicz.cinema.dto.MovieOfferDto;
import com.szachnowicz.cinema.dto.MovieOfferSimplyDto;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class MovieOfferAdapterTest {
    private MovieOfferAdapter movieOfferAdapter;

    @BeforeClass
    void setUp() {

        MovieOfferDto movieOfferDto = new MovieOfferDto();
        movieOfferDto.setMovieName("Matrix");
        movieOfferDto.setAvailableSeatCodes(Collections.singletonList("A1"));
        movieOfferDto.setCinemaHallId(1L);
        movieOfferDto.setMovieDetails("Movie is cool");
        movieOfferDto.setScreeningTime(LocalDateTime.now().plusMinutes(120));
        movieOfferDto.setMovieOfferId(12L);

        movieOfferAdapter = new MovieOfferAdapter(MockUtils.getMockedMovieOfferRepo(movieOfferDto));
}


    @Test
    void getAllMovieOffersInDateRage_method_happy_path_test_1() {
        Collection<MovieOfferSimplyDto> list = movieOfferAdapter
                .getAllMovieOffersInDateRage(LocalDateTime.now(), LocalDateTime.now().plusMinutes(119));
        assertEquals(list.size(), 0);
    }

    @Test
    void getAllMovieOffersInDateRage_method_happy_path_test_2() {
        Collection<MovieOfferSimplyDto> list = movieOfferAdapter
                .getAllMovieOffersInDateRage(LocalDateTime.now().plusHours(1), LocalDateTime.now().plusHours(3));
        assertEquals(list.size(), 1);

        MovieOfferSimplyDto movieOffer = list.stream().findFirst().get();
        assertEquals((long) movieOffer.getMovieOfferId(), 12L);
        LocalDateTime time = LocalDateTime.now().plusMinutes(120);
        assertTrue(time.isAfter(movieOffer.getScreeningTime()) ||
                time.isEqual(movieOffer.getScreeningTime()));
        assertEquals(movieOffer.getMovieName(), "Matrix");

    }

    @Test
    public void getMovieOffer_method_all_fields_test() {
        MovieOfferDto movieOffer = movieOfferAdapter.getMovieOffer(1L);

        assertEquals(movieOffer.getMovieName(), "Matrix");
        assertEquals(movieOffer.getAvailableSeatCodes(), Collections.singletonList("A1"));
        assertEquals((long) movieOffer.getCinemaHallId(), 1L);
        assertEquals(movieOffer.getMovieDetails(), "Movie is cool");
        assertEquals((long) movieOffer.getMovieOfferId(), 12L);
        LocalDateTime time = LocalDateTime.now().plusMinutes(120);
        assertTrue(time.isAfter(movieOffer.getScreeningTime()) ||
                time.isEqual(movieOffer.getScreeningTime()));


    }
}