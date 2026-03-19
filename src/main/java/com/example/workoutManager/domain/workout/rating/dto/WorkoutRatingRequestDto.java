package com.example.workoutManager.domain.workout.rating.dto;

import com.example.workoutManager.shared.enums.MuscleGroupEnum;
import com.example.workoutManager.shared.enums.VisibilityEnum;
import com.example.workoutManager.shared.enums.WorkoutLevelEnum;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.UUID;

public record WorkoutRatingRequestDto(@NotBlank UUID workoutId,
                                      @NotBlank UUID userId,
                                      @NotBlank @Size(min = 1, max = 5) Byte value,
                                      @Nullable String comment,
                                      @NotNull Boolean isPublic) {
}
