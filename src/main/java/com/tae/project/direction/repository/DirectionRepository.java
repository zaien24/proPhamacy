package com.tae.project.direction.repository;

import com.tae.project.direction.entity.Direction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DirectionRepository extends JpaRepository<Direction, Long> {
}
