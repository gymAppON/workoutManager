package com.example.workoutManager.domain.workout;

import com.example.workoutManager.domain.workout.dto.WorkoutRequestDto;
import com.example.workoutManager.domain.workout.dto.WorkoutResponseDto;

import java.util.List;
import java.util.UUID;

public interface WorkoutService {
    WorkoutResponseDto create(WorkoutRequestDto request);

    WorkoutResponseDto getById(UUID id);

    List<WorkoutResponseDto> getAll();

    WorkoutResponseDto update(UUID id, WorkoutRequestDto request);

    void delete(UUID id);
}
