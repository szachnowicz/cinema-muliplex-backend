package com.szachnowicz.cinema.domain.repo;

import com.szachnowicz.cinema.domain.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReservationJpaRepository extends JpaRepository<ReservationEntity, Integer> {

    Optional<ReservationEntity> findByReservationUuid(String code);
}
