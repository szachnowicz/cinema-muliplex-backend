package com.szachnowicz.cinema.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.Set;

@Entity(name = "movie_offer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieOfferEntity extends BaseEntity {
    private Long cinemaId;
    private LocalDateTime screeningTime;
    private String movieName;
    private String movieDetails;
    @ManyToOne
    private CinemaHallEntity cinemaHall;
    @OneToMany(mappedBy = "movieOffer")
    private Set<ReservationEntity> reservations;

}
