package com.example.workoutManager.domain.workout.rating;

import com.example.workoutManager.domain.workout.rating.dto.WorkoutRatingRequestDto;
import com.example.workoutManager.domain.workout.rating.dto.WorkoutRatingResponseDto;

import java.util.List;
import java.util.UUID;

public interface WorkoutRatingService {
    WorkoutRatingResponseDto create(WorkoutRatingRequestDto request);

    WorkoutRatingResponseDto getById(UUID id);

    List<WorkoutRatingResponseDto> getAll();

    WorkoutRatingResponseDto update(UUID id, WorkoutRatingRequestDto request);

    void delete(UUID id);
}
