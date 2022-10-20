package com.campgladiator.codechallenge.exception;

public class TrainerNumberGenerationException extends RuntimeException {

    public TrainerNumberGenerationException(final String message) {
        super(message);
    }

    public TrainerNumberGenerationException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public TrainerNumberGenerationException(final Throwable cause) {
        super(cause);
    }
}
