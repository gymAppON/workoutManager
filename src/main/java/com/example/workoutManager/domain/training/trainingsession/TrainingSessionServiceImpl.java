package com.example.workoutManager.domain.training.trainingsession;

import com.example.workoutManager.domain.training.trainingsession.dto.TrainingSessionRequestDto;
import com.example.workoutManager.domain.training.trainingsession.dto.TrainingSessionResponseDto;
import com.example.workoutManager.domain.workout.WorkoutService;
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
public class TrainingSessionServiceImpl implements TrainingSessionService {
    private final TrainingSessionRepository trainingSessionRepository;
    private final TrainingSessionMapper trainingSessionMapper;
    private final WorkoutService workoutService;
    private static final String OBJECT_NAME = "TrainingSession";

    @Override
    public TrainingSessionResponseDto create(TrainingSessionRequestDto request) {
        workoutService.getById(request.workoutId());

        TrainingSessionEntity session = trainingSessionRepository.save(trainingSessionMapper.toEntity(request));

        log.info("{}: {} (Id: {}) was created", LogEnum.SERVICE, OBJECT_NAME, session.getId());
        return trainingSessionMapper.toResponse(session);
    }

    @Override
    public TrainingSessionResponseDto getById(UUID id) {
        TrainingSessionEntity session = findById(id);

        log.info("{}: {} (Id: {}) was found", LogEnum.SERVICE, OBJECT_NAME, id);
        return trainingSessionMapper.toResponse(session);
    }

    @Override
    public List<TrainingSessionResponseDto> getAll() {
        List<TrainingSessionResponseDto> sessions = trainingSessionMapper.toResponseDtoList(trainingSessionRepository.findAll());

        log.info("{}: all {} were obtained", LogEnum.SERVICE, OBJECT_NAME);
        return sessions;
    }

    @Override
    public TrainingSessionResponseDto update(UUID id, TrainingSessionRequestDto request) {
        workoutService.getById(request.workoutId());
        TrainingSessionEntity fromDb = findById(id);

        TrainingSessionEntity fromRequest = trainingSessionMapper.toEntity(request);
        fromRequest.setId(fromDb.getId());

        TrainingSessionEntity updated = trainingSessionRepository.save(fromRequest);
        log.info("{}: {} (Id: {}) was updated", LogEnum.SERVICE, OBJECT_NAME, id);
        return trainingSessionMapper.toResponse(updated);
    }

    @Override
    public void delete(UUID id) {
        trainingSessionRepository.deleteById(id);

        log.info("{}: {} (Id: {}) was deleted", LogEnum.SERVICE, OBJECT_NAME, id);
    }

    public TrainingSessionEntity findById(UUID id) {
        return trainingSessionRepository.findById(id).orElseThrow(() -> new CustomNotFoundException(OBJECT_NAME, id));
    }
}
