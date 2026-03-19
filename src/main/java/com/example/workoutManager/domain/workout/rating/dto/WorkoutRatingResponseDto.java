package com.example.workoutManager.domain.workout.rating.dto;

import java.util.UUID;

public record WorkoutRatingResponseDto(UUID id,
                                       UUID workoutId,
                                       UUID userId,
                                       Byte value,
                                       String comment,
                                       Boolean isPublic) { }
