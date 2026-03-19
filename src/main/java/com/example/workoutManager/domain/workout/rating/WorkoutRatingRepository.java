package com.example.workoutManager.domain.workout.rating;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WorkoutRatingRepository extends JpaRepository<WorkoutRatingEntity, UUID> {
}
