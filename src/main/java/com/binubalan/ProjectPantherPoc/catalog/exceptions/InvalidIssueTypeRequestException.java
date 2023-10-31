package com.binubalan.ProjectPantherPoc.catalog.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidIssueTypeRequestException extends ResponseStatusException {
    public InvalidIssueTypeRequestException(String message){
        super(HttpStatus.BAD_REQUEST, message);
    }
}
