package com.binubalan.ProjectPantherPoc.catalog.controllers;

import com.binubalan.ProjectPantherPoc.catalog.entities.IssueType;
import com.binubalan.ProjectPantherPoc.catalog.models.IssueTypeCreateRequest;
import com.binubalan.ProjectPantherPoc.catalog.abstractions.IssueTypeService;
import com.binubalan.ProjectPantherPoc.catalog.exceptions.InvalidIssueTypeRequestException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v*/issue-type")
@AllArgsConstructor
public class IssueTypeController {

    @Autowired
    private final IssueTypeService issueTypeService;

    @GetMapping
    public IssueType get(@RequestParam Long issueId){
        return issueTypeService.findById(issueId);
    }

    @GetMapping(value = "/getAll")
    public List<IssueType> getAll(){
        return issueTypeService.findAll();
    }

    @PostMapping
    public IssueType create(@RequestBody IssueTypeCreateRequest createRequest) throws InvalidIssueTypeRequestException {
        return issueTypeService.createIssue(createRequest);
    }

    @DeleteMapping
    public void delete(@RequestParam long issueId){
        issueTypeService.deleteById(issueId);
    }


}
