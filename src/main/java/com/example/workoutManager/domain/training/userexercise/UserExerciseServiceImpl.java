package com.example.workoutManager.domain.training.userexercise;

import com.example.workoutManager.domain.exercise.ExerciseService;
import com.example.workoutManager.domain.training.trainingsession.TrainingSessionService;
import com.example.workoutManager.domain.training.userexercise.dto.UserExerciseRequestDto;
import com.example.workoutManager.domain.training.userexercise.dto.UserExerciseResponseDto;
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
public class UserExerciseServiceImpl implements UserExerciseService {
    private final UserExerciseRepository userExerciseRepository;
    private final UserExerciseMapper userExerciseMapper;
    private final TrainingSessionService trainingSessionService;
    private final ExerciseService exerciseService;
    private final RabbitMessageService messageService;
    private static final String OBJECT_NAME = "UserExercise";

    @Override
    public UserExerciseResponseDto create(UserExerciseRequestDto request) {
        // Request to User Manager, to check if user exist
        checkUserId(request.userId());
        //End of check
        trainingSessionService.getById(request.trainingSessionId());
        exerciseService.getById(request.exerciseId());

        UserExerciseEntity entity = userExerciseRepository.save(userExerciseMapper.toEntity(request));

        log.info("{}: {} (Id: {}) was created", LogEnum.SERVICE, OBJECT_NAME, entity.getId());
        return userExerciseMapper.toResponse(entity);
    }

    @Override
    public UserExerciseResponseDto getById(UUID id) {
        UserExerciseEntity entity = findById(id);

        log.info("{}: {} (Id: {}) was found", LogEnum.SERVICE, OBJECT_NAME, id);
        return userExerciseMapper.toResponse(entity);
    }

    @Override
    public List<UserExerciseResponseDto> getAll() {
        List<UserExerciseResponseDto> list = userExerciseMapper.toResponseDtoList(userExerciseRepository.findAll());

        log.info("{}: all {} were obtained", LogEnum.SERVICE, OBJECT_NAME);
        return list;
    }

    @Override
    public UserExerciseResponseDto update(UUID id, UserExerciseRequestDto request) {
        // Request to User Manager, to check if user exist
        checkUserId(request.userId());
        //End of check
        trainingSessionService.getById(request.trainingSessionId());
        exerciseService.getById(request.exerciseId());
        UserExerciseEntity fromDb = findById(id);

        UserExerciseEntity fromRequest = userExerciseMapper.toEntity(request);
        fromRequest.setId(fromDb.getId());

        UserExerciseEntity updated = userExerciseRepository.save(fromRequest);
        log.info("{}: {} (Id: {}) was updated", LogEnum.SERVICE, OBJECT_NAME, id);
        return userExerciseMapper.toResponse(updated);
    }

    @Override
    public void delete(UUID id) {
        userExerciseRepository.deleteById(id);

        log.info("{}: {} (Id: {}) was deleted", LogEnum.SERVICE, OBJECT_NAME, id);
    }

    private UserExerciseEntity findById(UUID id) {
        return userExerciseRepository.findById(id).orElseThrow(() -> new CustomNotFoundException(OBJECT_NAME, id));
    }
    private void checkUserId(UUID userId) {
        if (!messageService.checkUserExistsViaRabbit(userId)){
            throw new CustomNotFoundException("User", userId);
        }
    }
}
