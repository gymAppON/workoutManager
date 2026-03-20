package com.example.workoutManager.domain.training.exerciseset.dto;

import com.example.workoutManager.shared.enums.WeightUnitEnum;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record ExerciseSetRequestDto(
        @NotNull UUID userExerciseId,
        @NotNull Integer setNumber,
        Float weight,
        WeightUnitEnum weightUnit,
        Integer reps,
        Integer distance,
        Integer duration,
        @Min(1) @Max(10) Integer effort,
        String notes
) {
}
