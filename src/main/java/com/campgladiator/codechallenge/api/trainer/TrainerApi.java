package com.campgladiator.codechallenge.api.trainer;

import com.campgladiator.codechallenge.api.ApiErrorResponse;
import com.campgladiator.codechallenge.api.trainer.request.NewTrainerRequest;
import com.campgladiator.codechallenge.api.trainer.response.TrainerResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Tag(name= "Trainer API")
@RequestMapping(value = "/trainer", produces = MediaType.APPLICATION_JSON_VALUE)
public interface TrainerApi {

    @Operation(summary = "Create a new trainer")
    @ApiResponse(
        responseCode = "201",
        description = "Trainer was created",
        content = {
            @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = TrainerResponse.class)
            )
        }
    )
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    TrainerResponse createNewTrainer(@Valid @RequestBody final NewTrainerRequest requestPayload);

    @Operation(summary = "Retrieve trainer by trainer number")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "The trainer was retrieved successfully",
            content = {
                @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = TrainerResponse.class)
                )
            }
        ),
        @ApiResponse(
            responseCode = "404",
            description = "No trainer was found with the given trainer number",
            content = {
                @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ApiErrorResponse.class)
                )
            }
        )
    })
    @GetMapping("/{trainerNumber}")
    TrainerResponse getTrainerDetails(
        @Parameter(description = "The trainer number", required = true)
        @PathVariable final String trainerNumber);

}
