package com.example.workoutManager.domain.training.trainingsession.dto;

import com.example.workoutManager.shared.enums.TrainingTypeEnum;
import java.time.Instant;
import java.util.UUID;

public record TrainingSessionResponseDto(UUID id,
                                         UUID workoutId,
                                         TrainingTypeEnum type,
                                         String notes,
                                         Instant startedAt,
                                         Instant endAt,
                                         Instant plannedAt) {
}
