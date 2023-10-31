package com.binubalan.ProjectPantherPoc.catalog.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class IssueTypeNotExitingException extends ResponseStatusException {
    public IssueTypeNotExitingException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}