package com.szachnowicz.cinema.domain;


import com.szachnowicz.cinema.dto.TicketType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity(name = "reserved_seats")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservedSeatEntity extends BaseEntity {
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "reservation_id")
    private ReservationEntity reservation;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "seat_id")
    private SeatEntity seat;
    @Enumerated
    private TicketType ticketType;
}
