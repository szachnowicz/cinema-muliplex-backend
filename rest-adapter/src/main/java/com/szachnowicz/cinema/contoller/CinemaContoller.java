package com.szachnowicz.cinema.contoller;


import com.szachnowicz.cinema.dto.MovieOfferDto;
import com.szachnowicz.cinema.dto.MovieOfferSimplyDto;
import com.szachnowicz.cinema.dto.ReservationRequest;
import com.szachnowicz.cinema.dto.ReservationResponse;
import com.szachnowicz.cinema.port.primary.MovieOfferPort;
import com.szachnowicz.cinema.port.primary.ReservationCreatorPort;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/cinema/api")
class CinemaContoller {
    private final MovieOfferPort movieOfferPort;
    private final ReservationCreatorPort reservationCreatorPort;

    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "beginOfScreening",
                    value = "example: 2020-05-10 15:20",
                    required = true,
                    paramType = "query"),
            @ApiImplicitParam(
                    name = "endOfScreening",
                    value = "example: 2020-06-10 23:20",
                    required = true,
                    paramType = "query")
    })
    @GetMapping("/movies")
    public Collection<MovieOfferSimplyDto> getMovieOffert(@RequestParam String beginOfScreening, @RequestParam String endOfScreening) {
        return movieOfferPort.getAllMovieOffersInDateRage(DateUtil.parse(beginOfScreening), DateUtil.parse(endOfScreening));
    }

    @GetMapping("/movies/{id}")
    public MovieOfferDto getMovieById(@PathVariable Long id) {
        return movieOfferPort.getMovieOffer(id);
    }

    @PostMapping("/reservations")
    public ReservationResponse reserveSeats(@RequestBody ReservationRequest reservationRequest) {
        return reservationCreatorPort.createReservation(reservationRequest);
    }

    @GetMapping("/confirmations")
    public String confirmReservation(@RequestParam String code) {
        return reservationCreatorPort.confirmReservation(code);
    }


}
