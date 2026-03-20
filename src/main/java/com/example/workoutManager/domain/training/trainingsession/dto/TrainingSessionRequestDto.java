package com.example.workoutManager.domain.training.trainingsession.dto;

import com.example.workoutManager.shared.enums.TrainingTypeEnum;
import jakarta.validation.constraints.NotNull;
import java.time.Instant;
import java.util.UUID;

public record TrainingSessionRequestDto(UUID workoutId,
                                        @NotNull TrainingTypeEnum type,
                                        String notes,
                                        Instant startedAt,
                                        Instant endAt,
                                        Instant plannedAt) {
}
