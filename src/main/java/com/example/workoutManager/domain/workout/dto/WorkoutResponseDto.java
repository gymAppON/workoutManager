package com.example.workoutManager.domain.workout.dto;

import com.example.workoutManager.shared.enums.MuscleGroupEnum;
import com.example.workoutManager.shared.enums.VisibilityEnum;
import com.example.workoutManager.shared.enums.WorkoutLevelEnum;

import java.util.List;
import java.util.UUID;

public record WorkoutResponseDto(UUID id,
                                 UUID userId,
                                 String name,
                                 String imageUrl,
                                 String description,
                                 MuscleGroupEnum muscleGroup,
                                 WorkoutLevelEnum workoutLevel,
                                 VisibilityEnum visibility,
                                 List<UUID> exerciseIds) { }
