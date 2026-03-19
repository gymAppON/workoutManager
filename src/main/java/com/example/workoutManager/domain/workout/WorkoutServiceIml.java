package com.example.workoutManager.domain.workout;

import com.example.workoutManager.domain.workout.dto.WorkoutRequestDto;
import com.example.workoutManager.domain.workout.dto.WorkoutResponseDto;
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
public class WorkoutServiceIml implements WorkoutService {
    private final WorkoutRepository workoutRepository;
    private final WorkoutMapper workoutMapper;
    private final RabbitMessageService messageService;
    private static final String OBJECT_NAME = "Workout";

    @Override
    public WorkoutResponseDto create(WorkoutRequestDto request) {
        // Request to User Manager, to check if user exist
        checkUserId(request.userId());
        //End of check
        WorkoutEntity workout = workoutRepository.save(workoutMapper.toEntity(request));

        log.info("{}: {} (Id: {}) was created", LogEnum.SERVICE, OBJECT_NAME, workout.getId());
        return workoutMapper.toResponse(workout);
    }

    @Override
    public WorkoutResponseDto getById(UUID id) {
        WorkoutEntity workout = findById(id);

        log.info("{}: {} (Id: {}) was found", LogEnum.SERVICE, OBJECT_NAME, id);
        return workoutMapper.toResponse(workout);
    }

    @Override
    public List<WorkoutResponseDto> getAll() {
        List<WorkoutResponseDto> workouts = workoutMapper.toResponseDtoList(workoutRepository.findAll());

        log.info("{}: all {} were obtained", LogEnum.SERVICE, OBJECT_NAME);
        return workouts;
    }

    @Override
    public WorkoutResponseDto update(UUID id, WorkoutRequestDto request) {
        WorkoutEntity fromDb = findById(id);
        checkUserId(request.userId());

        WorkoutEntity fromRequest = workoutMapper.toEntity(request);
        fromRequest.setId(fromDb.getId());

        WorkoutEntity updated = workoutRepository.save(fromRequest);
        log.info("{}: {} (Id: {}) was updated", LogEnum.SERVICE, OBJECT_NAME, id);
        return workoutMapper.toResponse(updated);
    }

    @Override
    public void delete(UUID id) {
        workoutRepository.deleteById(id);

        log.info("{}: {} (Id: {}) was deleted", LogEnum.SERVICE, OBJECT_NAME, id);
    }

    //FIND BY
    public WorkoutEntity findById(UUID id) {
        return workoutRepository.findById(id).orElseThrow(() -> new CustomNotFoundException(OBJECT_NAME, id));
    }

    private void checkUserId(UUID userId) {
        if (!messageService.checkUserExistsViaRabbit(userId)){
            throw new CustomNotFoundException("User", userId);
        }
    }
}
