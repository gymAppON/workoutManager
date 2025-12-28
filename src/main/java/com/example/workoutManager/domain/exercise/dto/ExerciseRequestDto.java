package com.example.workoutManager.domain.exercise.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record ExerciseRequestDto(@Nullable UUID userId,
                                 @NotBlank @Size(min = 2, max = 50) String name,
                                 @NotBlank String equipment,
                                 @NotBlank String description) {
}
