package com.example.workoutManager.domain.exercise;

import com.example.workoutManager.domain.exercise.dto.ExerciseRequestDto;
import com.example.workoutManager.domain.exercise.dto.ExerciseResponseDto;
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
public class ExerciseServiceImpl implements ExerciseService {
    private final ExerciseRepository exerciseRepository;
    private final ExerciseMapper exerciseMapper;
    private final RabbitMessageService messageService;
    private static final String OBJECT_NAME = "Exercise";

    @Override
    public ExerciseResponseDto create(ExerciseRequestDto request) {
       // Request to User Manager, to check if user exist
        if (!messageService.checkUserExistsViaRabbit(request.userId())){
            throw new CustomNotFoundException("User", request.userId());
        }
        //End of check
        ExerciseEntity exercise = exerciseRepository.save(exerciseMapper.toEntity(request));

        log.info("{}: {} (Id: {}) was created", LogEnum.SERVICE, OBJECT_NAME, exercise.getId());
        return exerciseMapper.toResponse(exercise);
    }

    @Override
    public ExerciseResponseDto getById(UUID id) {
        ExerciseEntity exerciseEntity = findById(id);

        log.info("{}: {} (Id: {}) was found", LogEnum.SERVICE, OBJECT_NAME, id);
        return exerciseMapper.toResponse(exerciseEntity);
    }

    @Override
    public List<ExerciseResponseDto> getAll() {
        List<ExerciseResponseDto> exercises = exerciseMapper.toResponseDtoList(exerciseRepository.findAll());

        messageService.sendMessage("Test Message");

        log.info("{}: all {} were obtained", LogEnum.SERVICE, OBJECT_NAME);
        return exercises;
    }

    @Override
    public ExerciseResponseDto update(UUID id, ExerciseRequestDto request) {
        ExerciseEntity fromDb = findById(id);

        if (!messageService.checkUserExistsViaRabbit(request.userId())){
            throw new CustomNotFoundException("User", request.userId());
        }

        ExerciseEntity updated = exerciseRepository.save(exerciseMapper.toEntity(request));
        log.info("{}: {} (Id: {}) was updated", LogEnum.SERVICE, OBJECT_NAME, id);
        return exerciseMapper.toResponse(updated);
    }

    @Override
    public void delete(UUID id) {
        exerciseRepository.deleteById(id);

        log.info("{}: {} (Id: {}) was deleted", LogEnum.SERVICE, OBJECT_NAME, id);
    }

    //FIND BY
    public ExerciseEntity findById(UUID id) {
        return exerciseRepository.findById(id).orElseThrow(() -> new CustomNotFoundException(OBJECT_NAME, id));
    }
}
