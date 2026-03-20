package com.example.workoutManager.domain.training.trainingsession;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TrainingSessionRepository extends JpaRepository<TrainingSessionEntity, UUID> {
}
