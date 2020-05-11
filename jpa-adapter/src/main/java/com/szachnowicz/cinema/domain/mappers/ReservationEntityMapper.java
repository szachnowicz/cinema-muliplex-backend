package com.szachnowicz.cinema.domain.mappers;

import com.szachnowicz.cinema.domain.ReservationEntity;
import com.szachnowicz.cinema.dto.ReservationDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = ReservedSeatMapper.class)
public interface ReservationEntityMapper extends AbstractMapper<ReservationDto, ReservationEntity> {

}
