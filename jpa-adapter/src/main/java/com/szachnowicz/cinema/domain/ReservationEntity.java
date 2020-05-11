package com.szachnowicz.cinema.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity(name = "reservation")
@Setter
@Getter
@NoArgsConstructor
@Accessors(chain = true)
public class ReservationEntity extends BaseEntity {

    private LocalDateTime reservationTime;
    @ManyToOne(cascade = CascadeType.ALL)
    private MovieOfferEntity movieOffer;
    @OneToMany(mappedBy = "reservation")
    private Set<ReservedSeatEntity> seats = new HashSet<>();
    private BigDecimal price;
    private String name;
    private String lastName;
    private boolean isConfirmed;
    private String reservationUuid = UUID.randomUUID().toString();

}
