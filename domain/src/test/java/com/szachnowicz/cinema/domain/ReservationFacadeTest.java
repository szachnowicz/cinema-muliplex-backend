package com.szachnowicz.cinema.domain;

import com.szachnowicz.cinema.dto.MovieOfferDto;
import com.szachnowicz.cinema.dto.ReservationRequest;
import com.szachnowicz.cinema.dto.ReservationResponse;
import com.szachnowicz.cinema.dto.ReservedSeatDto;
import com.szachnowicz.cinema.dto.TicketType;
import com.szachnowicz.cinema.dto.error.BusinessErrorCodes;
import com.szachnowicz.cinema.dto.error.BusinessException;
import com.szachnowicz.cinema.port.primary.ReservationCreatorPort;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.expectThrows;

public class ReservationFacadeTest {
    ReservationCreatorPort creatorPort;

    @BeforeTest
    public void setUp() {

        MovieOfferDto movieOfferDto = new MovieOfferDto();
        movieOfferDto.setMovieName("Matrix");
        movieOfferDto.setAvailableSeatCodes(Collections.singletonList("A1"));
        movieOfferDto.setCinemaHallId(1L);
        movieOfferDto.setMovieDetails("Movie is cool");
        movieOfferDto.setScreeningTime(LocalDateTime.now().plusMinutes(120));
        movieOfferDto.setMovieOfferId(12L);


        List<String> reservedSeats = Arrays.asList("A2", "A3", "A4");

        creatorPort = new ReservationFacade(
                MockUtils.getMovedReservationRepository("1234-1234", reservedSeats),
                MockUtils.getMockedMovieOfferRepo(movieOfferDto),
                MockUtils.getMockedSeatRepo(Collections.singletonList("A1")),
                MockUtils.getMockedEnvironment("9999")
        );
    }

    @Test
    public void createReservation_method_happy_Path_Test() {

        ReservationRequest reservationRequest = new ReservationRequest()
                .setMovieId(1L)
                .setSeatDto(Collections.singletonList(new ReservedSeatDto("A1", TicketType.ADULT)))
                .setName("Adam").setLastName("Małysz");

        ReservationResponse reservation = creatorPort.createReservation(reservationRequest);

        assertEquals(reservation.getMovieName(), "Matrix");
        assertEquals(reservation.getConfirmationUrl(), createReservationUrl("1234-1234", "9999"));
        assertEquals(reservation.getSeats(), Collections.singletonList("A1"));
        assertEquals(reservation.getPrice(), TicketType.ADULT.getPrice());
        assertEquals((long) reservation.getMovieHallId(), 1L);

    }

    @Test
    public void createReservation_method_already_reserved_seat_test() {

        ReservationRequest reservationRequest = new ReservationRequest()
                .setMovieId(1L)
                .setSeatDto(Collections.singletonList(new ReservedSeatDto("A2", TicketType.ADULT)))
                .setName("Adam").setLastName("Małysz");

        BusinessException businessException = expectThrows(BusinessException.class, () -> creatorPort.createReservation(reservationRequest));
        assertEquals(businessException.getErrorCode(), BusinessErrorCodes.ALREADY_RESERVED);
    }

    @Test
    public void createReservation_method_seats_not_adjacent_test() {

        ReservationRequest reservationRequest = new ReservationRequest()
                .setMovieId(1L)
                .setSeatDto(Arrays.asList(new ReservedSeatDto("A1", TicketType.ADULT), new ReservedSeatDto("A9", TicketType.ADULT)))
                .setName("Adam").setLastName("Małysz");

        BusinessException businessException = expectThrows(BusinessException.class, () -> creatorPort.createReservation(reservationRequest));
        assertEquals(businessException.getErrorCode(), BusinessErrorCodes.SEAT_NOT_ADJACENT);
    }


    @Test
    public void confirmReservation_happy_path_test() {

        String result = creatorPort.confirmReservation("1234-1234");
        assertEquals(result, "SUCCESS");
    }

    private String createReservationUrl(String uuid, String port) {
        String ipAddress = "http://localhost:" + port;
        try {
            ipAddress = InetAddress.getLocalHost().getHostAddress() + ":" + port + "/";
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return ipAddress + "cinema/api/confirmations/?code=" + uuid;
    }
}