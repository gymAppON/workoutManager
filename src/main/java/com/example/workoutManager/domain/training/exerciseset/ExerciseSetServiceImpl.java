package com.example.workoutManager.domain.training.exerciseset;

import com.example.workoutManager.domain.training.exerciseset.dto.ExerciseSetRequestDto;
import com.example.workoutManager.domain.training.exerciseset.dto.ExerciseSetResponseDto;
import com.example.workoutManager.domain.training.userexercise.UserExerciseService;
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
public class ExerciseSetServiceImpl implements ExerciseSetService {
    private final ExerciseSetRepository exerciseSetRepository;
    private final ExerciseSetMapper exerciseSetMapper;
    private final UserExerciseService userExerciseService;
    private static final String OBJECT_NAME = "ExerciseSet";

    @Override
    public ExerciseSetResponseDto create(ExerciseSetRequestDto request) {
        userExerciseService.getById(request.userExerciseId());

        ExerciseSetEntity entity = exerciseSetRepository.save(exerciseSetMapper.toEntity(request));

        log.info("{}: {} (Id: {}) was created", LogEnum.SERVICE, OBJECT_NAME, entity.getId());
        return exerciseSetMapper.toResponse(entity);
    }

    @Override
    public ExerciseSetResponseDto getById(UUID id) {
        ExerciseSetEntity entity = findById(id);

        log.info("{}: {} (Id: {}) was found", LogEnum.SERVICE, OBJECT_NAME, id);
        return exerciseSetMapper.toResponse(entity);
    }

    @Override
    public List<ExerciseSetResponseDto> getAll() {
        List<ExerciseSetResponseDto> list = exerciseSetMapper.toResponseDtoList(exerciseSetRepository.findAll());

        log.info("{}: all {} were obtained", LogEnum.SERVICE, OBJECT_NAME);
        return list;
    }

    @Override
    public ExerciseSetResponseDto update(UUID id, ExerciseSetRequestDto request) {
        userExerciseService.getById(request.userExerciseId());
        ExerciseSetEntity fromDb = findById(id);

        ExerciseSetEntity fromRequest = exerciseSetMapper.toEntity(request);
        fromRequest.setId(fromDb.getId());

        ExerciseSetEntity updated = exerciseSetRepository.save(fromRequest);
        log.info("{}: {} (Id: {}) was updated", LogEnum.SERVICE, OBJECT_NAME, id);
        return exerciseSetMapper.toResponse(updated);
    }

    @Override
    public void delete(UUID id) {
        exerciseSetRepository.deleteById(id);

        log.info("{}: {} (Id: {}) was deleted", LogEnum.SERVICE, OBJECT_NAME, id);
    }

    private ExerciseSetEntity findById(UUID id) {
        return exerciseSetRepository.findById(id).orElseThrow(() -> new CustomNotFoundException(OBJECT_NAME, id));
    }
}
