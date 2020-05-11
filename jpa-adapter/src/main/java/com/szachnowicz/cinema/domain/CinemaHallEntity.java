package com.szachnowicz.cinema.domain;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity(name = "cinema_hall")
@Getter
public class CinemaHallEntity extends BaseEntity {

    private int seatNumber;
    private Long cinemaId;
    private String hallName;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "cinema_hall_id")
    private Set<SeatEntity> seats;


}
