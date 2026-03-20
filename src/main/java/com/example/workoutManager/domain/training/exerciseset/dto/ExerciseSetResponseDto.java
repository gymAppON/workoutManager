package com.example.workoutManager.domain.training.exerciseset.dto;

import com.example.workoutManager.shared.enums.WeightUnitEnum;
import java.util.UUID;

public record ExerciseSetResponseDto(
        UUID id,
        UUID userExerciseId,
        Integer setNumber,
        Float weight,
        WeightUnitEnum weightUnit,
        Integer reps,
        Integer distance,
        Integer duration,
        Integer effort,
        String notes
) {
}
