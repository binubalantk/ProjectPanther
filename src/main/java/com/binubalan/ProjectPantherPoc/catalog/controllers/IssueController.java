package com.binubalan.ProjectPantherPoc.catalog.controllers;

import com.binubalan.ProjectPantherPoc.catalog.abstractions.IssueService;
import com.binubalan.ProjectPantherPoc.catalog.exceptions.IssueNotExistsException;
import com.binubalan.ProjectPantherPoc.catalog.exceptions.IssueTypeExistsException;
import com.binubalan.ProjectPantherPoc.catalog.models.IssueModel;
import com.binubalan.ProjectPantherPoc.catalog.models.IssueRequestModel;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/issue")
@AllArgsConstructor
public class IssueController {
    @Autowired
    private final IssueService issueService;

    @GetMapping
    public IssueModel get(@RequestParam long id) throws IssueNotExistsException {
        return issueService.getById(id);
    }

    @GetMapping(value = "/getAll")
    public List<IssueModel> getAll(){
        return issueService.getAll();
    }

    @GetMapping(value = "/getByIssueType")
    public List<IssueModel> getByIssueType(@RequestParam long issueTypeId) throws IssueTypeExistsException {
        return issueService.getAllByType(issueTypeId);
    }

    @PostMapping
    public IssueModel create(@RequestBody IssueRequestModel issueModel){
        return issueService.createOrUpdate(issueModel);
    }

    @DeleteMapping
    public void delete(@RequestParam long id) throws IssueNotExistsException {
        issueService.deleteById(id);
    }

    @PutMapping("/addChild")
    public void addChild(@RequestParam long parentIssueId, @RequestParam long childIssueId) throws IssueNotExistsException{
        issueService.addChild(parentIssueId, childIssueId);
    }
}
