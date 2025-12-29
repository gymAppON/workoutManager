package com.example.workoutManager.domain.exercise;

import com.example.workoutManager.domain.exercise.dto.ExerciseRequestDto;
import com.example.workoutManager.domain.exercise.dto.ExerciseResponseDto;

import java.util.List;
import java.util.UUID;

public interface ExerciseService {
    ExerciseResponseDto create(ExerciseRequestDto request);

    ExerciseResponseDto getById(UUID id);

    List<ExerciseResponseDto> getAll();

    ExerciseResponseDto update(UUID id, ExerciseRequestDto request);

    void delete(UUID id);
}
