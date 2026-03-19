package com.example.workoutManager.domain.workout.dto;

import com.example.workoutManager.shared.enums.MuscleGroupEnum;
import com.example.workoutManager.shared.enums.VisibilityEnum;
import com.example.workoutManager.shared.enums.WorkoutLevelEnum;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.UUID;

public record WorkoutRequestDto(@Nullable UUID userId,
                                @NotBlank @Size(min = 2, max = 50) String name,
                                @Nullable String imageUrl,
                                @NotBlank String description,
                                @NotNull MuscleGroupEnum muscleGroup,
                                @NotNull WorkoutLevelEnum workoutLevel,
                                @NotNull VisibilityEnum visibility,
                                @NotNull List<UUID> exerciseIds) {
}
