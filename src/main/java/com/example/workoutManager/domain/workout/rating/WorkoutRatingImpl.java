package com.example.workoutManager.domain.workout.rating;

import com.example.workoutManager.domain.exercise.ExerciseEntity;
import com.example.workoutManager.domain.workout.WorkoutService;
import com.example.workoutManager.domain.workout.rating.dto.WorkoutRatingRequestDto;
import com.example.workoutManager.domain.workout.rating.dto.WorkoutRatingResponseDto;
import com.example.workoutManager.infrastructure.rabbitmq.RabbitMessageService;
import com.example.workoutManager.shared.enums.LogEnum;
import com.example.workoutManager.shared.exception.exceptions.general.CustomNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class WorkoutRatingImpl implements WorkoutRatingService {
    private final WorkoutService workoutService;

    private final WorkoutRatingRepository workoutRatingRepository;
    private final WorkoutRatingMapper workoutRatingMapper;
    private final RabbitMessageService messageService;
    private static final String OBJECT_NAME = "Workout Rating";

    @Override
    public WorkoutRatingResponseDto create(WorkoutRatingRequestDto request) {
        checkWorkoutAndUserId(request.workoutId(),  request.userId());

        WorkoutRatingEntity workoutRating = workoutRatingRepository.save(workoutRatingMapper.toEntity(request));

        log.info("{}: {} (Id: {}) was created", LogEnum.SERVICE, OBJECT_NAME, workoutRating.getId());
        return workoutRatingMapper.toResponse(workoutRating);
    }

    @Override
    public WorkoutRatingResponseDto getById(UUID id) {
        WorkoutRatingEntity workoutRating = findById(id);

        log.info("{}: {} (Id: {}) was found", LogEnum.SERVICE, OBJECT_NAME, id);
        return workoutRatingMapper.toResponse(workoutRating);
    }

    @Override
    public List<WorkoutRatingResponseDto> getAll() {
        List<WorkoutRatingResponseDto> workoutRating = workoutRatingMapper.toResponseDtoList(workoutRatingRepository.findAll());

        log.info("{}: all {} were obtained", LogEnum.SERVICE, OBJECT_NAME);
        return workoutRating;
    }

    @Override
    public WorkoutRatingResponseDto update(UUID id, WorkoutRatingRequestDto request) {
        WorkoutRatingEntity fromDb = findById(id);
        checkWorkoutAndUserId(request.workoutId(),  request.userId());

        WorkoutRatingEntity fromRequest = workoutRatingMapper.toEntity(request);
        fromRequest.setId(fromDb.getId());

        WorkoutRatingEntity updated = workoutRatingRepository.save(fromRequest);
        log.info("{}: {} (Id: {}) was updated", LogEnum.SERVICE, OBJECT_NAME, id);
        return workoutRatingMapper.toResponse(updated);
    }

    @Override
    public void delete(UUID id) {
        workoutRatingRepository.deleteById(id);

        log.info("{}: {} (Id: {}) was deleted", LogEnum.SERVICE, OBJECT_NAME, id);
    }

    //FIND BY
    public WorkoutRatingEntity findById(UUID id) {
        return workoutRatingRepository.findById(id).orElseThrow(() -> new CustomNotFoundException(OBJECT_NAME, id));
    }

    private void checkWorkoutAndUserId(UUID workoutId, UUID userId) {
        try {
            workoutService.getById(workoutId);
        } catch (CustomNotFoundException e) {
            throw new CustomNotFoundException("Workot", workoutId);
        }
        if (!messageService.checkUserExistsViaRabbit(userId)){
            throw new CustomNotFoundException("User", userId);
        }
    }
}
