package com.szachnowicz.cinema.domain.mappers;

import com.szachnowicz.cinema.domain.ReservedSeatEntity;
import com.szachnowicz.cinema.dto.ReservedSeatDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReservedSeatMapper extends AbstractMapper<ReservedSeatDto, ReservedSeatEntity> {


    List<ReservedSeatEntity> fromDto(List<ReservedSeatDto> seatList);
}
