package com.campgladiator.codechallenge.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@JsonInclude(Include.NON_NULL)
public class ApiErrorResponse {

    @JsonProperty("status_code")
    private final int statusCode;

    private final String message;

    private final Instant timestamp;

    @JsonProperty("field_validation_errors")
    private Map<String, String> fieldValidationErrors;

    public ApiErrorResponse(final int statusCode, final String message, final Instant timestamp) {
        this.statusCode = statusCode;
        this.message = message;
        this.timestamp = timestamp;
    }

    public ApiErrorResponse(final int statusCode, final String message) {
        this.statusCode = statusCode;
        this.message = message;
        this.timestamp = Instant.now();
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void addFieldValidationError(final String field, final String errorDescription) {
        if (Objects.isNull(this.fieldValidationErrors)) {
            this.fieldValidationErrors = new HashMap<>();
        }

        this.fieldValidationErrors.put(field, errorDescription);
    }

    public Map<String, String> getFieldValidationErrors() {
        return fieldValidationErrors;
    }


}
