package com.campgladiator.codechallenge.api;

import com.campgladiator.codechallenge.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrorResponse handleResourceNotFoundException(final NotFoundException e) {
        return new ApiErrorResponse(
            HttpStatus.NOT_FOUND.value(),
            e.getMessage()
        );
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponse handleHttpMessageNotReadableException(final HttpMessageNotReadableException e) {

        final String exceptionErrorDetails = e.getCause().getMessage();

        return new ApiErrorResponse(
            HttpStatus.BAD_REQUEST.value(),
            String.format("Error parsing the JSON object: %s", exceptionErrorDetails)
        );

    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ApiErrorResponse handleMethodArgumentNotValidException(final MethodArgumentNotValidException e) {

        final ApiErrorResponse validationError = new ApiErrorResponse(
            HttpStatus.UNPROCESSABLE_ENTITY.value(),
            "One or more fields have an error"
        );

        e.getBindingResult().getFieldErrors().forEach(error -> {
            validationError.addFieldValidationError(error.getField(), error.getDefaultMessage());
        });

        return validationError;
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiErrorResponse handleAllExceptions(final Exception e) {
        return new ApiErrorResponse(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            e.getMessage()
        );
    }
}
