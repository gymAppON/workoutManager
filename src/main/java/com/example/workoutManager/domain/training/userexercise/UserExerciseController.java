package com.example.workoutManager.domain.training.userexercise;

import com.example.workoutManager.domain.training.userexercise.dto.UserExerciseRequestDto;
import com.example.workoutManager.domain.training.userexercise.dto.UserExerciseResponseDto;
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
    @RequestMapping("/training/user-exercises")
@Slf4j
@RequiredArgsConstructor
public class UserExerciseController {
    private static final String URI_WITH_ID = "/{id}";
    private final UserExerciseService userExerciseService;
    private static final String OBJECT_NAME = "UserExercise";

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new user exercise", description = "Creates a new user exercise")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User exercise created successfully",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UserExerciseResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "User exercise not found")
    })
    public UserExerciseResponseDto create(@RequestBody UserExerciseRequestDto request) {
        UserExerciseResponseDto responseDto = userExerciseService.create(request);
        log.info("{}: {} created: {}", LogEnum.CONTROLLER, OBJECT_NAME, request);

        return responseDto;
    }

    @GetMapping(URI_WITH_ID)
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get user exercise by ID", description = "Get a user exercise by their unique ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User exercise found by id",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UserExerciseResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "User exercise not found")
    })
    public UserExerciseResponseDto getById(@PathVariable UUID id) {
        UserExerciseResponseDto exercise = userExerciseService.getById(id);
        log.info("{}: Get {} by id: {}", LogEnum.CONTROLLER, OBJECT_NAME, id);
        return exercise;
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all user exercises", description = "Retrieves a list of all user exercises.")
    @ApiResponse(responseCode = "200", description = "List of user exercises retrieved successfully",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = UserExerciseResponseDto.class)))
    public List<UserExerciseResponseDto> getAll() {
        List<UserExerciseResponseDto> exercises = userExerciseService.getAll();
        log.info("{}: Show all {}s", LogEnum.CONTROLLER, OBJECT_NAME);
        return exercises;
    }

    @PutMapping(URI_WITH_ID)
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update user exercise details", description = "Updates the details of an existing user exercise.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User exercise updated successfully",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UserExerciseResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid data"),
            @ApiResponse(responseCode = "404", description = "User exercise not found")
    })
    public UserExerciseResponseDto update(@PathVariable UUID id, @RequestBody UserExerciseRequestDto request) {
        UserExerciseResponseDto updatedExercise = userExerciseService.update(id, request);
        log.info("{}: Updated {} with id: {}", LogEnum.CONTROLLER, OBJECT_NAME, id);
        return updatedExercise;
    }

    @DeleteMapping(URI_WITH_ID)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a user exercise", description = "Deletes a user exercise by their ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User exercise deleted successfully"),
            @ApiResponse(responseCode = "404", description = "User exercise not found")
    })
    public void delete(@PathVariable UUID id) {
        userExerciseService.delete(id);
        log.info("{}: Deleted {} with id: {}", LogEnum.CONTROLLER, OBJECT_NAME, id);
    }
}
