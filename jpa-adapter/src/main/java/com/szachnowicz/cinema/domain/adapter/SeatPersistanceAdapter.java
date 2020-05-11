package com.szachnowicz.cinema.domain.adapter;

import com.szachnowicz.cinema.domain.MovieOfferEntity;
import com.szachnowicz.cinema.domain.ReservedSeatEntity;
import com.szachnowicz.cinema.domain.SeatEntity;
import com.szachnowicz.cinema.domain.repo.MovieOfferJpaRepository;
import com.szachnowicz.cinema.domain.repo.SeatJpaRepository;
import com.szachnowicz.cinema.dto.error.BusinessException;
import com.szachnowicz.cinema.port.secondary.SeatRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.szachnowicz.cinema.dto.error.BusinessErrorCodes.SEAT_NOT_EXIST;

@Service
@RequiredArgsConstructor
class SeatPersistanceAdapter implements SeatRepo {
    private final SeatJpaRepository seatJpaRepository;
    private final MovieOfferJpaRepository movieOfferJpaRepository;


    @Override
    public void validateSeatCodes(List<String> seatCodes, Long movieHallId) {

        seatCodes.forEach(code -> seatJpaRepository.findByCinemaHallIdAndCode(movieHallId, code)
                .orElseThrow(() -> new BusinessException(SEAT_NOT_EXIST, String.format("Given '%s' Seat dose not exist", code))));


    }

    @Override
    public List<String> getAvailableSeatCodes(Long movieOfferId) {
        MovieOfferEntity entity = movieOfferJpaRepository.getOne(movieOfferId);

        List<SeatEntity> reservedSeats = entity.getReservations()
                .stream().flatMap(s -> s.getSeats().stream())
                .map(ReservedSeatEntity::getSeat)
                .collect(Collectors.toList());

        return (entity.getCinemaHall().getSeats().stream().filter(
                s -> !reservedSeats.contains(s)).map(SeatEntity::getCode).sorted().collect(Collectors.toList()));
    }
}


