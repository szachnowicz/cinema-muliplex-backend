package com.szachnowicz.cinema.domain.adapter;

import com.szachnowicz.cinema.domain.MovieOfferEntity;
import com.szachnowicz.cinema.domain.ReservationEntity;
import com.szachnowicz.cinema.domain.ReservedSeatEntity;
import com.szachnowicz.cinema.domain.SeatEntity;
import com.szachnowicz.cinema.domain.mappers.ReservedSeatMapper;
import com.szachnowicz.cinema.domain.repo.MovieOfferJpaRepository;
import com.szachnowicz.cinema.domain.repo.ReservationJpaRepository;
import com.szachnowicz.cinema.domain.repo.ReservedSeatJpaRepository;
import com.szachnowicz.cinema.domain.repo.SeatJpaRepository;
import com.szachnowicz.cinema.dto.ReservationDto;
import com.szachnowicz.cinema.dto.ReservedSeatDto;
import com.szachnowicz.cinema.dto.error.BusinessException;
import com.szachnowicz.cinema.port.secondary.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.szachnowicz.cinema.dto.error.BusinessErrorCodes.ALREADY_RESERVED;
import static com.szachnowicz.cinema.dto.error.BusinessErrorCodes.MOVIE_ID_NOT_EXIST;

@Service
@RequiredArgsConstructor
class ReservationPersistanceAdpater implements ReservationRepository {

    private final ReservationJpaRepository reservationJpaRepository;
    private final MovieOfferJpaRepository movieOfferJpaRepository;
    private final SeatJpaRepository seatJpaRepository;

    private final ReservedSeatJpaRepository reservedSeatJpaRepository;

    private final ReservedSeatMapper reservedSeatMapper;

    @Transactional
    @Override
    public String saveReservation(ReservationDto reservationDto) {

        MovieOfferEntity movieOfferEntity = movieOfferJpaRepository
                .findById(reservationDto.getMovieOfferId())
                .orElseThrow(() -> new BusinessException(MOVIE_ID_NOT_EXIST));


        ReservationEntity reservationEntity = new ReservationEntity()
                .setPrice(reservationDto.getPrice())
                .setMovieOffer(movieOfferEntity)
                .setName(reservationDto.getName())
                .setLastName(reservationDto.getLastName())
                .setReservationTime(reservationDto.getReservationTime());

        reserveSeats(reservationDto, reservationEntity);

        return reservationEntity.getReservationUuid();
    }

    @Override
    public void validateIfSeatNotReserved(List<String> seatCodes, Long movieOfferId) {

        MovieOfferEntity movieOfferEntity = movieOfferJpaRepository
                .findById(movieOfferId)
                .orElseThrow(() -> new BusinessException(MOVIE_ID_NOT_EXIST));

        movieOfferEntity.getReservations().stream()
                .flatMap(r -> r.getSeats().stream())
                .map(r -> r.getSeat().getCode())
                .filter(seatCodes::contains)
                .findAny().ifPresent(s -> {
            throw new BusinessException(ALREADY_RESERVED, String.format("Seat - '%s' already reserved", s));
        });
    }

    @Override
    @Transactional
    public boolean confirmReservation(String code) {
        Optional<ReservationEntity> reservationEntity = reservationJpaRepository.findByReservationUuid(code);
        if (!reservationEntity.isPresent()) {
            return false;
        }
        ReservationEntity entity = reservationEntity.get();
        entity.setConfirmed(true);
        return true;
    }

    private Set<ReservedSeatEntity> reserveSeats(ReservationDto reservationDto,
                                                 ReservationEntity reservationEntity) {
        return reservationDto
                .getSeats()
                .stream()
                .map(s -> persistSingleReservedSeat(s, reservationEntity))
                .collect(Collectors.toSet());
    }

    @Transactional
    private ReservedSeatEntity persistSingleReservedSeat(ReservedSeatDto dto, ReservationEntity reservation) {

        SeatEntity seatEntity = seatJpaRepository.findByCinemaHallIdAndCode(reservation.getMovieOffer().getCinemaHall().getId(), dto.getSeatCode()).get();

        ReservedSeatEntity reservedSeatEntity = new ReservedSeatEntity();
        reservedSeatEntity.setTicketType(dto.getTicketType());
        reservedSeatEntity.setSeat(seatEntity);
        reservedSeatEntity.setReservation(reservation);
        return reservedSeatJpaRepository.save(reservedSeatEntity);
    }

}
