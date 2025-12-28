package com.example.workoutManager.domain.exercise;

import com.example.workoutManager.domain.exercise.dto.ExerciseRequestDto;
import com.example.workoutManager.domain.exercise.dto.ExerciseResponseDto;
import com.example.workoutManager.infrastructure.config.CustomMapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", config = CustomMapperConfig.class)
public interface ExerciseMapper {

    @Mapping(target = "id", ignore = true)
    ExerciseEntity toEntity(ExerciseRequestDto request);
    ExerciseResponseDto toResponse(ExerciseEntity exerciseEntity);

    List<ExerciseResponseDto> toResponseDtoList(List<ExerciseEntity> entities);
    List<ExerciseEntity> toEntityList(List<ExerciseResponseDto> dtos);
}
