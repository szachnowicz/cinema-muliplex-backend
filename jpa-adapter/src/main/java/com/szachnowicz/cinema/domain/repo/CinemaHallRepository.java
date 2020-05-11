package com.szachnowicz.cinema.domain.repo;

import com.szachnowicz.cinema.domain.CinemaHallEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CinemaHallRepository extends JpaRepository<CinemaHallEntity, Long> {
}
