package com.szachnowicz.cinema.domain.repo;

import com.szachnowicz.cinema.domain.ReservedSeatEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservedSeatJpaRepository extends JpaRepository<ReservedSeatEntity, Long> {



}
