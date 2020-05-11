package com.szachnowicz.cinema.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "seat")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SeatEntity extends BaseEntity {
    private String code;
    @Column(name = "cinema_hall_id")
    private Long cinemaHallId;

}
