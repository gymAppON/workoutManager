package com.example.workoutManager.domain.training.exerciseset;

import com.example.workoutManager.domain.training.exerciseset.dto.ExerciseSetRequestDto;
import com.example.workoutManager.domain.training.exerciseset.dto.ExerciseSetResponseDto;
import com.example.workoutManager.infrastructure.config.CustomMapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", config = CustomMapperConfig.class)
public interface ExerciseSetMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    ExerciseSetEntity toEntity(ExerciseSetRequestDto request);

    ExerciseSetResponseDto toResponse(ExerciseSetEntity entity);

    List<ExerciseSetResponseDto> toResponseDtoList(List<ExerciseSetEntity> entities);
}
