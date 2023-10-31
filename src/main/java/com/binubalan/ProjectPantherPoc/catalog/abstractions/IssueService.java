package com.binubalan.ProjectPantherPoc.catalog.abstractions;
import com.binubalan.ProjectPantherPoc.catalog.exceptions.IssueNotExistsException;
import com.binubalan.ProjectPantherPoc.catalog.models.IssueModel;
import com.binubalan.ProjectPantherPoc.catalog.models.IssueRequestModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IssueService {
    IssueModel createOrUpdate(IssueRequestModel issue);
    IssueModel getById(long id) throws IssueNotExistsException;
    List<IssueModel> getAll();
    List<IssueModel> getAllByType(long issueTypeId);
    void deleteById(long id) throws IssueNotExistsException;
    void addChild(long parentIssueId, long childIssueId) throws IssueNotExistsException;
}
