package com.example.workoutManager.domain.exercise.dto;

import java.util.UUID;

public record ExerciseResponseDto(UUID id,
                                  UUID userId,
                                  String name,
                                  String equipment,
                                  String description) { }
