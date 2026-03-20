package com.example.workoutManager.domain.training.userexercise;

import com.example.workoutManager.domain.training.userexercise.dto.UserExerciseRequestDto;
import com.example.workoutManager.domain.training.userexercise.dto.UserExerciseResponseDto;
import com.example.workoutManager.infrastructure.config.CustomMapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", config = CustomMapperConfig.class)
public interface UserExerciseMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    UserExerciseEntity toEntity(UserExerciseRequestDto request);

    UserExerciseResponseDto toResponse(UserExerciseEntity entity);

    List<UserExerciseResponseDto> toResponseDtoList(List<UserExerciseEntity> entities);
}
