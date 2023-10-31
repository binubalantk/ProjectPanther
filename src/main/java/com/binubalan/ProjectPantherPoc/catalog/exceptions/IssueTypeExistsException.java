package com.binubalan.ProjectPantherPoc.catalog.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class IssueTypeExistsException extends ResponseStatusException {
    public IssueTypeExistsException(String message){
        super(HttpStatus.INTERNAL_SERVER_ERROR, message);
    }
}
