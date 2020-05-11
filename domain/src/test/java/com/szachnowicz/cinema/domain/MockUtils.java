package com.szachnowicz.cinema.domain;

import com.szachnowicz.cinema.dto.MovieOfferDto;
import com.szachnowicz.cinema.dto.ReservationDto;
import com.szachnowicz.cinema.dto.error.BusinessErrorCodes;
import com.szachnowicz.cinema.dto.error.BusinessException;
import com.szachnowicz.cinema.port.secondary.MovieOfferRepo;
import com.szachnowicz.cinema.port.secondary.ReservationRepository;
import com.szachnowicz.cinema.port.secondary.SeatRepo;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MockUtils {
    static MovieOfferRepo getMockedMovieOfferRepo(MovieOfferDto movieOfferDto) {
        return new MovieOfferRepo() {
            @Override
            public MovieOfferDto getMovieById(Long movieId) {
                return movieOfferDto;
            }

            @Override
            public Collection<MovieOfferDto> getAllMovieOffersInDateRage(LocalDateTime from, LocalDateTime to) {
                if (from.isBefore(movieOfferDto.getScreeningTime()) && !movieOfferDto.getScreeningTime().isAfter(to)) {
                    return Collections.singletonList(movieOfferDto);
                } else {
                    return Collections.emptyList();
                }
            }

        };
    }


    static ReservationRepository getMovedReservationRepository(String confirmationUuid,List<String> reservedSeats) {
        return new ReservationRepository() {
            @Override
            public String saveReservation(ReservationDto reservationDto) {
                return confirmationUuid;
            }

            @Override
            public void validateIfSeatNotReserved(List<String> seatCodes, Long cinemaHallId) {

                if (seatCodes.stream().anyMatch(reservedSeats::contains)) {
                    throw new BusinessException(BusinessErrorCodes.ALREADY_RESERVED);
                }
            }

            @Override
            public boolean confirmReservation(String code) {
                return true;
            }
        };
    }

    static SeatRepo getMockedSeatRepo(List<String> availableSeatCodes) {
        return new SeatRepo() {
            @Override
            public void validateSeatCodes(List<String> seatCodes, Long movieHallId) {
            }

            @Override
            public List<String> getAvailableSeatCodes(Long movieOfferId) {
                return availableSeatCodes;
            }
        };
    }

    static Environment getMockedEnvironment(String port) {
        return new Environment() {
            @Override
            public String[] getActiveProfiles() {
                return new String[0];
            }

            @Override
            public String[] getDefaultProfiles() {
                return new String[0];
            }

            @Override
            public boolean acceptsProfiles(String... strings) {
                return false;
            }

            @Override
            public boolean acceptsProfiles(Profiles profiles) {
                return false;
            }

            @Override
            public boolean containsProperty(String s) {
                return false;
            }

            @Override
            public String getProperty(String s) {
                return port;
            }

            @Override
            public String getProperty(String s, String s1) {
                return null;
            }

            @Override
            public <T> T getProperty(String s, Class<T> aClass) {
                return null;
            }

            @Override
            public <T> T getProperty(String s, Class<T> aClass, T t) {
                return null;
            }

            @Override
            public String getRequiredProperty(String s) throws IllegalStateException {
                return null;
            }

            @Override
            public <T> T getRequiredProperty(String s, Class<T> aClass) throws IllegalStateException {
                return null;
            }

            @Override
            public String resolvePlaceholders(String s) {
                return null;
            }

            @Override
            public String resolveRequiredPlaceholders(String s) throws IllegalArgumentException {
                return null;
            }
        };
    }
}
