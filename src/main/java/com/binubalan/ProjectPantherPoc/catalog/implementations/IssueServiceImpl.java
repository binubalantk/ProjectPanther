package com.binubalan.ProjectPantherPoc.catalog.implementations;

import com.binubalan.ProjectPantherPoc.catalog.abstractions.IssueRepo;
import com.binubalan.ProjectPantherPoc.catalog.abstractions.IssueService;
import com.binubalan.ProjectPantherPoc.catalog.abstractions.IssueTypeRepo;
import com.binubalan.ProjectPantherPoc.catalog.entities.Issue;
import com.binubalan.ProjectPantherPoc.catalog.entities.IssueType;
import com.binubalan.ProjectPantherPoc.catalog.exceptions.IssueNotExistsException;
import com.binubalan.ProjectPantherPoc.catalog.exceptions.IssueTypeExistsException;
import com.binubalan.ProjectPantherPoc.catalog.models.IssueModel;
import com.binubalan.ProjectPantherPoc.catalog.models.IssueRequestModel;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service("issueService")
@AllArgsConstructor
public class IssueServiceImpl implements IssueService {

    @Autowired
    private final IssueRepo issueRepo;

    @Autowired
    private final IssueTypeRepo issueTypeRepo;

    @Override
    public IssueModel createOrUpdate(IssueRequestModel issue) {
        LocalDateTime now = LocalDateTime.now();
        Issue issueEntity = issue.toEntity();
        if(issue.getId() < 1){
            issueEntity.setCreatedOn(now);
        } else {
            Issue existingIssue = issueRepo
                .findById(issue.getId())
                .orElseThrow(() -> new IssueNotExistsException("Issue is not exists"));
            issueEntity.setCreatedOn(existingIssue.getCreatedOn());
        }

        Optional<IssueType> issueTypeOptional = issueTypeRepo.findById(issue.getIssueTypeId());
        if(!issueTypeOptional.isPresent()){
            throw new IssueTypeExistsException("No such issue type exists");
        }
        issueEntity.setIssueType(issueTypeOptional.get());

        issueEntity.setLastUpdated(now);
        Issue issueResponse = issueRepo
            .save(issueEntity);
        return issueResponse.toDto();
    }

    @Override
    public IssueModel getById(long id) throws IssueNotExistsException {
        return issueRepo
            .findById(id)
            .orElseThrow(() -> new IssueNotExistsException("Issue is not exists"))
            .toDto();
    }

    @Override
    public List<IssueModel> getAll() {
        return issueRepo
            .findAll()
            .stream().map(Issue::toDto)
            .toList();
    }

    @Override
    public List<IssueModel> getAllByType(long issueTypeId) throws IssueTypeExistsException {
        Optional<IssueType> issueTypeOptional = issueTypeRepo.findById(issueTypeId);
        if(!issueTypeOptional.isPresent()){
            throw new IssueTypeExistsException("No such issue type exists");
        }
        return issueRepo
                .findIssuesByType(issueTypeOptional.get())
                .stream().map(Issue::toDto)
                .toList();
    }

    @Override
    public void deleteById(long id) throws IssueNotExistsException {
        Optional<Issue> issueOptional = issueRepo.findById(id);
        if(!issueOptional.isPresent()){
            throw new IssueNotExistsException("No such issue exists");
        }
        issueRepo.deleteById(id);
    }

    @Override
    public void addChild(long parentIssueId, long childIssueId) throws IssueNotExistsException {
        Optional<Issue> parentIssueOptional = issueRepo.findById(parentIssueId);
        if(!parentIssueOptional.isPresent()){
            throw new IssueNotExistsException("No such issue exists");
        }

        Optional<Issue> childIssueOptional = issueRepo.findById(parentIssueId);
        if(!childIssueOptional.isPresent()){
            throw new IssueNotExistsException("No such issue exists");
        }

        Issue childrenIssue = childIssueOptional.get();
        childrenIssue.setParent(parentIssueOptional.get());
        issueRepo.save(childrenIssue);
    }
}
