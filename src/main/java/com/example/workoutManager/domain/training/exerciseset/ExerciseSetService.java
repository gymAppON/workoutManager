package com.example.workoutManager.domain.training.exerciseset;

import com.example.workoutManager.domain.training.exerciseset.dto.ExerciseSetRequestDto;
import com.example.workoutManager.domain.training.exerciseset.dto.ExerciseSetResponseDto;

import java.util.List;
import java.util.UUID;

public interface ExerciseSetService {
    ExerciseSetResponseDto create(ExerciseSetRequestDto request);
    ExerciseSetResponseDto getById(UUID id);
    List<ExerciseSetResponseDto> getAll();
    ExerciseSetResponseDto update(UUID id, ExerciseSetRequestDto request);
    void delete(UUID id);
}
