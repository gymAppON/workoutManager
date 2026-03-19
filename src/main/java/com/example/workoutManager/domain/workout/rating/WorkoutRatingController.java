package com.example.workoutManager.domain.workout.rating;

import com.example.workoutManager.domain.workout.dto.WorkoutResponseDto;
import com.example.workoutManager.domain.workout.rating.dto.WorkoutRatingRequestDto;
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
@RequestMapping("/workout/rating")
@Slf4j
@RequiredArgsConstructor
public class WorkoutRatingController {
    private static final String URI_WITH_ID = "/{id}";
    private final WorkoutRatingService ratingService;
    private static final String OBJECT_NAME = "Workout Rating";

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new workout rating", description = "Creates a new workout rating")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Workout Rating created successfully",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = WorkoutResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Workout Rating not found")
    })
    public WorkoutRatingResponseDto create(@RequestBody WorkoutRatingRequestDto request) {
        WorkoutRatingResponseDto ratingResponseDto = ratingService.create(request);
        log.info("{}: {} created: {}", LogEnum.CONTROLLER,OBJECT_NAME, request);

        return ratingResponseDto;
    }

    @GetMapping(URI_WITH_ID)
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get workout rating by ID", description = "Get a workout rating by their unique ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Workout Rating found by id",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = WorkoutRatingResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Workout Rating not found")
    })
    public WorkoutRatingResponseDto getById(@PathVariable UUID id) {
        WorkoutRatingResponseDto ratingResponseDto = ratingService.getById(id);
        log.info("{}: Get {} by id: {}",LogEnum.CONTROLLER,OBJECT_NAME, id);
        return ratingResponseDto;
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all workouts ratings", description = "Retrieves a list of all workouts ratings.")
    @ApiResponse(responseCode = "200", description = "List of workouts ratings retrieved successfully",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = WorkoutRatingResponseDto.class)))
    public List<WorkoutRatingResponseDto> getAll() {
        List<WorkoutRatingResponseDto> workoutRatingList = ratingService.getAll();
        log.info("{}: Show all {}s",LogEnum.CONTROLLER,OBJECT_NAME);
        return workoutRatingList;
    }

    @PutMapping(URI_WITH_ID)
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update workout rating details", description = "Updates the details of an existing workout rating.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Workout Rating updated successfully",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = WorkoutRatingResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid data"),
            @ApiResponse(responseCode = "404", description = "Workout Rating not found")
    })
    public WorkoutRatingResponseDto update(@PathVariable UUID id, @RequestBody WorkoutRatingRequestDto request) {
        WorkoutRatingResponseDto updatedRating = ratingService.update(id, request);
        log.info("{}: Updated {} with id: {}", LogEnum.CONTROLLER,OBJECT_NAME, id);
        return updatedRating;
    }

    @DeleteMapping(URI_WITH_ID)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a workout rating", description = "Deletes a workout rating by their ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Workout Rating deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Workout Rating not found")
    })
    public void delete(@PathVariable UUID id) {
        ratingService.delete(id);
        log.info("{}: Deleted {} with id: {}",LogEnum.CONTROLLER,OBJECT_NAME, id);
    }
}
