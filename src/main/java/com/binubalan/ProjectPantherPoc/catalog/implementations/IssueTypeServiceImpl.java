package com.binubalan.ProjectPantherPoc.catalog.implementations;

import com.binubalan.ProjectPantherPoc.catalog.abstractions.IssueTypeRepo;
import com.binubalan.ProjectPantherPoc.catalog.entities.IssueType;
import com.binubalan.ProjectPantherPoc.catalog.exceptions.IssueTypeExistsException;
import com.binubalan.ProjectPantherPoc.catalog.exceptions.IssueTypeNotExitingException;
import com.binubalan.ProjectPantherPoc.catalog.models.IssueTypeCreateRequest;
import com.binubalan.ProjectPantherPoc.catalog.abstractions.IssueTypeService;
import com.binubalan.ProjectPantherPoc.catalog.exceptions.InvalidIssueTypeRequestException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("issueTypeService")
@AllArgsConstructor
public class IssueTypeServiceImpl implements IssueTypeService {

    @Autowired
    private final IssueTypeRepo issueTypeRepo;

    @Override
    public IssueType createIssue(IssueTypeCreateRequest createRequest) throws InvalidIssueTypeRequestException {
        if(createRequest.getName() == null || createRequest.getName().isEmpty()){
            throw new InvalidIssueTypeRequestException("No issue type name found");
        }

        Optional<IssueType> existingIssueType = issueTypeRepo.findIssueTypeByName(createRequest.getName());
        if(!existingIssueType.isEmpty()){
            throw new IssueTypeExistsException("The issue name already exists");
        }

        IssueType issueType = new IssueType(createRequest.getName());
        return issueTypeRepo.save(issueType);
    }

    @Override
    public List<IssueType> findAll() {
        return issueTypeRepo
                .findAll();
    }

    @Override
    public IssueType findById(long id) {
        return issueTypeRepo
                .findById(id)
                .orElseThrow(() ->
                        new IssueTypeNotExitingException("The issue is not existing"));
    }

    @Override
    public void deleteById(long id) {
        IssueType issueType = issueTypeRepo
                .findById(id)
                .orElseThrow(() ->
                        new IssueTypeNotExitingException("The issue is not existing"));
        issueTypeRepo.deleteById(issueType.getId());
    }
}
