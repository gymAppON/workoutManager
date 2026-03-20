package com.example.workoutManager.domain.training.userexercise.dto;

import java.util.UUID;

public record UserExerciseResponseDto(UUID id,
                                       UUID userId,
                                       UUID trainingSessionId,
                                       UUID exerciseId,
                                       String notes,
                                       Integer sets,
                                       Integer reps,
                                       Double weight) {
}
