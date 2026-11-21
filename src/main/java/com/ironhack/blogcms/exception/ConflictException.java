package com.ironhack.blogcms.exception;

/** Thrown when an operation would leave the data inconsistent. Mapped to 409. */
public class ConflictException extends RuntimeException {

    public ConflictException(String message) {
        super(message);
    }
}
