package com.example.workoutManager.domain.training.trainingsession;

import com.example.workoutManager.domain.training.trainingsession.dto.TrainingSessionRequestDto;
import com.example.workoutManager.domain.training.trainingsession.dto.TrainingSessionResponseDto;
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
@RequestMapping("/training/training-sessions")
@Slf4j
@RequiredArgsConstructor
public class TrainingSessionController {
    private static final String URI_WITH_ID = "/{id}";
    private final TrainingSessionService trainingSessionService;
    private static final String OBJECT_NAME = "TrainingSession";

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new training session", description = "Creates a new training session")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Training session created successfully",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = TrainingSessionResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Training session not found")
    })
    public TrainingSessionResponseDto create(@RequestBody TrainingSessionRequestDto request) {
        TrainingSessionResponseDto responseDto = trainingSessionService.create(request);
        log.info("{}: {} created: {}", LogEnum.CONTROLLER, OBJECT_NAME, request);

        return responseDto;
    }

    @GetMapping(URI_WITH_ID)
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get training session by ID", description = "Get a training session by their unique ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Training session found by id",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = TrainingSessionResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Training session not found")
    })
    public TrainingSessionResponseDto getById(@PathVariable UUID id) {
        TrainingSessionResponseDto session = trainingSessionService.getById(id);
        log.info("{}: Get {} by id: {}", LogEnum.CONTROLLER, OBJECT_NAME, id);
        return session;
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all training sessions", description = "Retrieves a list of all training sessions.")
    @ApiResponse(responseCode = "200", description = "List of training sessions retrieved successfully",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = TrainingSessionResponseDto.class)))
    public List<TrainingSessionResponseDto> getAll() {
        List<TrainingSessionResponseDto> sessions = trainingSessionService.getAll();
        log.info("{}: Show all {}s", LogEnum.CONTROLLER, OBJECT_NAME);
        return sessions;
    }

    @PutMapping(URI_WITH_ID)
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update training session details", description = "Updates the details of an existing training session.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Training session updated successfully",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = TrainingSessionResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid data"),
            @ApiResponse(responseCode = "404", description = "Training session not found")
    })
    public TrainingSessionResponseDto update(@PathVariable UUID id, @RequestBody TrainingSessionRequestDto request) {
        TrainingSessionResponseDto updatedSession = trainingSessionService.update(id, request);
        log.info("{}: Updated {} with id: {}", LogEnum.CONTROLLER, OBJECT_NAME, id);
        return updatedSession;
    }

    @DeleteMapping(URI_WITH_ID)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a training session", description = "Deletes a training session by their ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Training session deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Training session not found")
    })
    public void delete(@PathVariable UUID id) {
        trainingSessionService.delete(id);
        log.info("{}: Deleted {} with id: {}", LogEnum.CONTROLLER, OBJECT_NAME, id);
    }
}
