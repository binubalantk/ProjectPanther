package com.binubalan.ProjectPantherPoc.catalog.abstractions;

import com.binubalan.ProjectPantherPoc.catalog.entities.IssueType;
import com.binubalan.ProjectPantherPoc.catalog.models.IssueTypeCreateRequest;
import com.binubalan.ProjectPantherPoc.catalog.exceptions.InvalidIssueTypeRequestException;

import java.util.List;

public interface IssueTypeService {
    IssueType createIssue(IssueTypeCreateRequest createRequest) throws InvalidIssueTypeRequestException;
    List<IssueType> findAll();

    IssueType findById(long id);

    void deleteById(long id);
}
