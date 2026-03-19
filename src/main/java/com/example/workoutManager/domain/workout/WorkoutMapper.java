package com.example.workoutManager.domain.workout;

import com.example.workoutManager.domain.workout.dto.WorkoutRequestDto;
import com.example.workoutManager.domain.workout.dto.WorkoutResponseDto;
import com.example.workoutManager.infrastructure.config.CustomMapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", config = CustomMapperConfig.class)
public interface WorkoutMapper {
    @Mapping(target = "id", ignore = true)
    WorkoutEntity toEntity(WorkoutRequestDto request);
    WorkoutResponseDto toResponse(WorkoutEntity entity);

    List<WorkoutResponseDto> toResponseDtoList(List<WorkoutEntity> entities);
    List<WorkoutEntity> toEntityList(List<WorkoutResponseDto> dtos);
}
