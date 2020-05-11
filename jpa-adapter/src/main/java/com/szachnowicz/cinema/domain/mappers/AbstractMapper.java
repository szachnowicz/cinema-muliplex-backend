package com.szachnowicz.cinema.domain.mappers;

public interface AbstractMapper<DTO, ENT> {

    DTO toDto(ENT source);

    ENT fromDto(DTO target);

}
