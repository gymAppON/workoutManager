package com.example.workoutManager.domain.training.userexercise;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserExerciseRepository extends JpaRepository<UserExerciseEntity, UUID> {
}
