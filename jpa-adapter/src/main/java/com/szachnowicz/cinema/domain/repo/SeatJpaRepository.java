package com.szachnowicz.cinema.domain.repo;

import com.szachnowicz.cinema.domain.SeatEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface SeatJpaRepository extends JpaRepository<SeatEntity, Long> {
    Set<SeatEntity> findAllByCinemaHallIdAndCodeIn(Long cinemaHallId, List<String> codes);

    Optional<SeatEntity> findByCinemaHallIdAndCode(Long cinemaHallId, String code);
}
