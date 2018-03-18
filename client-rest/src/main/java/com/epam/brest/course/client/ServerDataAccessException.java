package com.epam.brest.course.client;

/**
 * Server Data Access Exception.
 */
public class ServerDataAccessException extends RuntimeException {

    public ServerDataAccessException(String message) {
        super(message);
    }

    public ServerDataAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}
