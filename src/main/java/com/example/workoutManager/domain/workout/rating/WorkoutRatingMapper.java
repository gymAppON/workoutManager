package com.example.workoutManager.domain.workout.rating;

import com.example.workoutManager.domain.workout.rating.dto.WorkoutRatingRequestDto;
import com.example.workoutManager.domain.workout.rating.dto.WorkoutRatingResponseDto;
import com.example.workoutManager.infrastructure.config.CustomMapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", config = CustomMapperConfig.class)
public interface WorkoutRatingMapper {
    @Mapping(target = "id", ignore = true)
    WorkoutRatingEntity toEntity(WorkoutRatingRequestDto request);
    WorkoutRatingResponseDto toResponse(WorkoutRatingEntity entity);

    List<WorkoutRatingResponseDto> toResponseDtoList(List<WorkoutRatingEntity> entities);
    List<WorkoutRatingEntity> toEntityList(List<WorkoutRatingResponseDto> dtos);
}
