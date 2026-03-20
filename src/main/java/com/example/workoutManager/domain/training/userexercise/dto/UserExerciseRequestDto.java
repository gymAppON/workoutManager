package com.example.workoutManager.domain.training.userexercise.dto;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record UserExerciseRequestDto(@NotNull UUID userId,
                                     @NotNull UUID trainingSessionId,
                                      @NotNull UUID exerciseId,
                                      String notes,
                                      Integer sets,
                                      Integer reps,
                                      Double weight) {
}
