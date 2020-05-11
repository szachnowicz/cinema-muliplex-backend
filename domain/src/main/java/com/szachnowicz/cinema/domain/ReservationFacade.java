package com.szachnowicz.cinema.domain;

import com.szachnowicz.cinema.dto.MovieOfferDto;
import com.szachnowicz.cinema.dto.ReservationDto;
import com.szachnowicz.cinema.dto.ReservationRequest;
import com.szachnowicz.cinema.dto.ReservationResponse;
import com.szachnowicz.cinema.dto.ReservedSeatDto;
import com.szachnowicz.cinema.dto.error.BusinessException;
import com.szachnowicz.cinema.port.primary.ReservationCreatorPort;
import com.szachnowicz.cinema.port.secondary.MovieOfferRepo;
import com.szachnowicz.cinema.port.secondary.ReservationRepository;
import com.szachnowicz.cinema.port.secondary.SeatRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.szachnowicz.cinema.dto.error.BusinessErrorCodes.SEAT_NOT_ADJACENT;

@Service
@RequiredArgsConstructor
class ReservationFacade implements ReservationCreatorPort {

    private final ReservationRepository reservationRepository;
    private final MovieOfferRepo movieOfferRepo;
    private final SeatRepo seatRepo;
    private final Environment environment;


    @Override
    public ReservationResponse createReservation(ReservationRequest reservationRequest) {

        MovieOfferDto movieOfferDto = movieOfferRepo.getMovieById(reservationRequest.getMovieId());

        validateReservationRequest(reservationRequest, movieOfferDto.getCinemaHallId());


        Reservation reservation =
                new Reservation()
                        .movieId(movieOfferDto.getMovieOfferId())
                        .reservationName(reservationRequest.getName(), reservationRequest.getLastName())
                        .addSeats(reservationRequest.getSeatDto())
                        .checkReservationTimeLimitation(movieOfferDto.getScreeningTime());

        ReservationDto reservationDto = reservation.toDto();


        String reservationUuid = reservationRepository.saveReservation(reservationDto);


        return ReservationResponse.builder()
                .movieHallId(movieOfferDto.getCinemaHallId())
                .movieName(movieOfferDto.getMovieName())
                .price(reservationDto.getPrice())
                .seats(toSeatCodes(reservationRequest))
                .reservationUrl(reservationUuid, environment.getProperty("server.port"))
                .build();

    }

    private List<String> toSeatCodes(ReservationRequest reservationRequest) {
        return reservationRequest.getSeatDto().stream().map(ReservedSeatDto::getSeatCode).collect(Collectors.toList());
    }

    private void validateReservationRequest(ReservationRequest reservationRequest, Long cinemaHallId) {
        List<String> seatCodes = toSeatCodes(reservationRequest);

        seatRepo.validateSeatCodes(seatCodes, cinemaHallId);
        if (!SeatsUtils.areSeatsCodesAdjacent(seatCodes)) {
            throw new BusinessException(SEAT_NOT_ADJACENT, String.format("Seats '%s' are not adjacent", seatCodes));
        }
        reservationRepository.validateIfSeatNotReserved(seatCodes, reservationRequest.getMovieId());
    }


    @Override
    public String confirmReservation(String code) {
        return reservationRepository.confirmReservation(code) ? "SUCCESS" : "FAILED";
    }
}
