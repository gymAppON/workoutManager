package com.example.workoutManager.domain.workout;

import com.example.workoutManager.domain.workout.dto.WorkoutRequestDto;
import com.example.workoutManager.domain.workout.dto.WorkoutResponseDto;
import com.example.workoutManager.domain.workout.rating.dto.WorkoutRatingResponseDto;
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
@RequestMapping("/workout/workouts")
@Slf4j
@RequiredArgsConstructor
public class WorkoutController {
    private static final String URI_WITH_ID = "/{id}";
    private final WorkoutService workoutService;
    private static final String OBJECT_NAME = "Workout";

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new workout", description = "Creates a new workout")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Workout created successfully",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = WorkoutResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Workout not found")
    })
    public WorkoutResponseDto create(@RequestBody WorkoutRequestDto request) {
        WorkoutResponseDto workoutResponseDto = workoutService.create(request);
        log.info("{}: {} created: {}", LogEnum.CONTROLLER,OBJECT_NAME, request);

        return workoutResponseDto;
    }

    @GetMapping(URI_WITH_ID)
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get workout by ID", description = "Get a workout by their unique ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Workout found by id",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = WorkoutResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Workout not found")
    })
    public WorkoutResponseDto getById(@PathVariable UUID id) {
        WorkoutResponseDto workout = workoutService.getById(id);
        log.info("{}: Get {} by id: {}",LogEnum.CONTROLLER,OBJECT_NAME, id);
        return workout;
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all workouts", description = "Retrieves a list of all workouts.")
    @ApiResponse(responseCode = "200", description = "List of workouts retrieved successfully",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = WorkoutResponseDto.class)))
    public List<WorkoutResponseDto> getAll() {
        List<WorkoutResponseDto> workouts = workoutService.getAll();
        log.info("{}: Show all {}s",LogEnum.CONTROLLER,OBJECT_NAME);
        return workouts;
    }

    @PutMapping(URI_WITH_ID)
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update workout details", description = "Updates the details of an existing workout.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Workout updated successfully",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = WorkoutResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid data"),
            @ApiResponse(responseCode = "404", description = "Workout not found")
    })
    public WorkoutResponseDto update(@PathVariable UUID id, @RequestBody WorkoutRequestDto request) {
        WorkoutResponseDto updatedWorkout = workoutService.update(id, request);
        log.info("{}: Updated {} with id: {}", LogEnum.CONTROLLER,OBJECT_NAME, id);
        return updatedWorkout;
    }

    @DeleteMapping(URI_WITH_ID)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a workout", description = "Deletes a workout by their ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Workout deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Workout not found")
    })
    public void delete(@PathVariable UUID id) {
        workoutService.delete(id);
        log.info("{}: Deleted {} with id: {}",LogEnum.CONTROLLER,OBJECT_NAME, id);
    }
}
