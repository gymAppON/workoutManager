package com.example.workoutManager.domain.exercise;

import com.example.workoutManager.domain.exercise.dto.ExerciseRequestDto;
import com.example.workoutManager.domain.exercise.dto.ExerciseResponseDto;
import com.example.workoutManager.shared.enums.LogEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/exercises")
@Slf4j
@RequiredArgsConstructor
public class ExerciseController {
    private static final String URI_WITH_ID = "/{id}";
    private final ExerciseService exerciseService;
    private static final String OBJECT_NAME = "Exercise";

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new exercise", description = "Creates a new exercise")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Exercise created successfully",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ExerciseResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Exercise not found")
    })
    public ExerciseResponseDto create(@RequestBody ExerciseRequestDto request) {
        ExerciseResponseDto exerciseResponseDto = exerciseService.create(request);
        log.info("{}: {} created: {}", LogEnum.CONTROLLER,OBJECT_NAME, request);

        return exerciseResponseDto;
    }

    @GetMapping(URI_WITH_ID)
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get exercise by ID", description = "Get a exercise by their unique ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Exercise found by id",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ExerciseResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Exercise not found")
    })
    public ExerciseResponseDto getById(@PathVariable UUID id) {
        ExerciseResponseDto exercise = exerciseService.getById(id);
        log.info("{}: Get {} by id: {}",LogEnum.CONTROLLER,OBJECT_NAME, id);
        return exercise;
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all exercises", description = "Retrieves a list of all exercises.")
    @ApiResponse(responseCode = "200", description = "List of exercises retrieved successfully",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ExerciseResponseDto.class)))
    public List<ExerciseResponseDto> getAll() {
        List<ExerciseResponseDto> exercises = exerciseService.getAll();
        log.info("{}: Show all {}s",LogEnum.CONTROLLER,OBJECT_NAME);
        return exercises;
    }

    @PutMapping(URI_WITH_ID)
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update exercise details", description = "Updates the details of an existing exercise.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Exercise updated successfully",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ExerciseResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid data"),
            @ApiResponse(responseCode = "404", description = "Exercise not found")
    })
    public ExerciseResponseDto update(@PathVariable UUID id, @RequestBody ExerciseRequestDto request) {
        ExerciseResponseDto updatedExercise = exerciseService.update(id, request);
        log.info("{}: Updated {} with id: {}", LogEnum.CONTROLLER,OBJECT_NAME, id);
        return updatedExercise;
    }

    @DeleteMapping(URI_WITH_ID)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a exercise", description = "Deletes a exercise by their ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Exercise deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Exercise not found")
    })
    public void delete(@PathVariable UUID id) {
        exerciseService.delete(id);
        log.info("{}: Deleted {} with id: {}",LogEnum.CONTROLLER,OBJECT_NAME, id);
    }
}
