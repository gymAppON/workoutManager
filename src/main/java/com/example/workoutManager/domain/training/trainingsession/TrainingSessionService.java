package com.example.workoutManager.domain.training.trainingsession;

import com.example.workoutManager.domain.training.trainingsession.dto.TrainingSessionRequestDto;
import com.example.workoutManager.domain.training.trainingsession.dto.TrainingSessionResponseDto;

import java.util.List;
import java.util.UUID;

public interface TrainingSessionService {
    TrainingSessionResponseDto create(TrainingSessionRequestDto request);

    TrainingSessionResponseDto getById(UUID id);

    List<TrainingSessionResponseDto> getAll();

    TrainingSessionResponseDto update(UUID id, TrainingSessionRequestDto request);

    void delete(UUID id);
}
