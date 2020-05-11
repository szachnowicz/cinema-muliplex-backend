package com.szachnowicz.cinema.domain.mappers;

import com.szachnowicz.cinema.domain.MovieOfferEntity;
import com.szachnowicz.cinema.domain.ReservedSeatEntity;
import com.szachnowicz.cinema.domain.SeatEntity;
import com.szachnowicz.cinema.dto.MovieOfferDto;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface MovieOfferMapper extends AbstractMapper<MovieOfferDto, MovieOfferEntity> {


    Collection<MovieOfferDto> toList(Collection<MovieOfferEntity> from);


    @Override
    @Mapping(source = "cinemaHall.id", target = "cinemaHallId")
    @Mapping(source = "id", target = "movieOfferId")
    MovieOfferDto toDto(MovieOfferEntity source);


    @AfterMapping
    default void mapAvailableSeats(@MappingTarget MovieOfferDto dto, MovieOfferEntity entity) {
        List<SeatEntity> reservedSeats = entity.getReservations()
                .stream().flatMap(s -> s.getSeats().stream())
                .map(ReservedSeatEntity::getSeat)
                .collect(Collectors.toList());

        dto.setAvailableSeatCodes(entity.getCinemaHall().getSeats().stream().filter(
                s -> !reservedSeats.contains(s)).map(SeatEntity::getCode).sorted().collect(Collectors.toList()));

    }
}
