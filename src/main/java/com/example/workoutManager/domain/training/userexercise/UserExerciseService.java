package com.example.workoutManager.domain.training.userexercise;

import com.example.workoutManager.domain.training.userexercise.dto.UserExerciseRequestDto;
import com.example.workoutManager.domain.training.userexercise.dto.UserExerciseResponseDto;

import java.util.List;
import java.util.UUID;

public interface UserExerciseService {
    UserExerciseResponseDto create(UserExerciseRequestDto request);

    UserExerciseResponseDto getById(UUID id);

    List<UserExerciseResponseDto> getAll();

    UserExerciseResponseDto update(UUID id, UserExerciseRequestDto request);

    void delete(UUID id);
}
