package com.szachnowicz.cinema.domain.repo;

import com.szachnowicz.cinema.domain.MovieOfferEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface MovieOfferJpaRepository extends JpaRepository<MovieOfferEntity, Long> {
    List<MovieOfferEntity> findAllByScreeningTimeBetween(LocalDateTime start, LocalDateTime End);
    MovieOfferEntity findByScreeningTime(LocalDateTime day);
}