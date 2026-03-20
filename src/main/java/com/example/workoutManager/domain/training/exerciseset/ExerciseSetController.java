package com.example.workoutManager.domain.training.exerciseset;

import com.example.workoutManager.domain.training.exerciseset.dto.ExerciseSetRequestDto;
import com.example.workoutManager.domain.training.exerciseset.dto.ExerciseSetResponseDto;
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
@RequestMapping("/training/exercise-sets")
@Slf4j
@RequiredArgsConstructor
public class ExerciseSetController {
    private static final String URI_WITH_ID = "/{id}";
    private final ExerciseSetService exerciseSetService;
    private static final String OBJECT_NAME = "ExerciseSet";

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new exercise set", description = "Creates a new exercise set")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Exercise set created successfully",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ExerciseSetResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "User exercise not found")
    })
    public ExerciseSetResponseDto create(@RequestBody ExerciseSetRequestDto request) {
        ExerciseSetResponseDto responseDto = exerciseSetService.create(request);
        log.info("{}: {} created: {}", LogEnum.CONTROLLER, OBJECT_NAME, request);

        return responseDto;
    }

    @GetMapping(URI_WITH_ID)
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get exercise set by ID", description = "Get an exercise set by its unique ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Exercise set found by id",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ExerciseSetResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Exercise set not found")
    })
    public ExerciseSetResponseDto getById(@PathVariable UUID id) {
        ExerciseSetResponseDto exerciseSet = exerciseSetService.getById(id);
        log.info("{}: Get {} by id: {}", LogEnum.CONTROLLER, OBJECT_NAME, id);
        return exerciseSet;
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all exercise sets", description = "Retrieves a list of all exercise sets.")
    @ApiResponse(responseCode = "200", description = "List of exercise sets retrieved successfully",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ExerciseSetResponseDto.class)))
    public List<ExerciseSetResponseDto> getAll() {
        List<ExerciseSetResponseDto> exerciseSets = exerciseSetService.getAll();
        log.info("{}: Show all {}s", LogEnum.CONTROLLER, OBJECT_NAME);
        return exerciseSets;
    }

    @PutMapping(URI_WITH_ID)
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update exercise set details", description = "Updates the details of an existing exercise set.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Exercise set updated successfully",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ExerciseSetResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid data"),
            @ApiResponse(responseCode = "404", description = "Exercise set not found")
    })
    public ExerciseSetResponseDto update(@PathVariable UUID id, @RequestBody ExerciseSetRequestDto request) {
        ExerciseSetResponseDto updatedExerciseSet = exerciseSetService.update(id, request);
        log.info("{}: Updated {} with id: {}", LogEnum.CONTROLLER, OBJECT_NAME, id);
        return updatedExerciseSet;
    }

    @DeleteMapping(URI_WITH_ID)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete an exercise set", description = "Deletes an exercise set by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Exercise set deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Exercise set not found")
    })
    public void delete(@PathVariable UUID id) {
        exerciseSetService.delete(id);
        log.info("{}: Deleted {} with id: {}", LogEnum.CONTROLLER, OBJECT_NAME, id);
    }
}
