package com.ruan.booktracker.book_tracker_api.exceptions;

import java.util.UUID;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String resource, UUID id) {
        super(resource + " not found. Id: " + id);
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
