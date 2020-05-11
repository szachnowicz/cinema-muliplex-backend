package com.szachnowicz.cinema.domain;

import com.szachnowicz.cinema.dto.ReservedSeatDto;
import com.szachnowicz.cinema.dto.TicketType;
import com.szachnowicz.cinema.dto.error.BusinessException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.szachnowicz.cinema.dto.error.BusinessErrorCodes.RESERVATION_TIME_LIMIT;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.expectThrows;


public class ReservationTest {


    private Reservation reservation;

    @BeforeTest
    void setUp() {
        reservation = new Reservation();
    }

    @Test
    void checkReservationTimeLimitationInRange() {
        LocalDateTime screeningTime = LocalDateTime.now().plusMinutes(30);
        reservation.checkReservationTimeLimitation(screeningTime);

    }

    @Test
    void shouldThrowWhen14MinutesAfterScreeningTime() {
        LocalDateTime screeningTime = LocalDateTime.now().plusMinutes(14);
        BusinessException businessException = expectThrows(BusinessException.class, () -> reservation.checkReservationTimeLimitation(screeningTime));
        assertEquals(businessException.getErrorCode(),RESERVATION_TIME_LIMIT);
    }

    @Test
    void addSeatMethod_Should_save_three_Seats_And_Sum_Ticked_Prices() {
        List<ReservedSeatDto> seats = Arrays.asList(
                new ReservedSeatDto("A1", TicketType.STUDENT),
                new ReservedSeatDto("A2", TicketType.CHILD),
                new ReservedSeatDto("A3", TicketType.ADULT)
        );
        reservation.addSeats(seats);
        assertEquals(3, reservation.toDto().getSeats().size());
        assertEquals(BigDecimal.valueOf(55.50), reservation.toDto().getPrice());
    }

    @Test
    void addSeatMethod_Should_do_nothing_when_gets_empty_list_seat() {
        reservation.addSeats(Collections.emptyList());
        assertEquals(0, reservation.toDto().getSeats().size());
        assertEquals(BigDecimal.ZERO, reservation.toDto().getPrice());

    }


}