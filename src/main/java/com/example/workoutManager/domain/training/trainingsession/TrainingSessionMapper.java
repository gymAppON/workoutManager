package com.example.workoutManager.domain.training.trainingsession;

import com.example.workoutManager.domain.training.trainingsession.dto.TrainingSessionRequestDto;
import com.example.workoutManager.domain.training.trainingsession.dto.TrainingSessionResponseDto;
import com.example.workoutManager.infrastructure.config.CustomMapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", config = CustomMapperConfig.class)
public interface TrainingSessionMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    TrainingSessionEntity toEntity(TrainingSessionRequestDto request);

    TrainingSessionResponseDto toResponse(TrainingSessionEntity entity);

    List<TrainingSessionResponseDto> toResponseDtoList(List<TrainingSessionEntity> entities);
}
