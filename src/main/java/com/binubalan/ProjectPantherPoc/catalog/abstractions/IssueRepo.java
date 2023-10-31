package com.binubalan.ProjectPantherPoc.catalog.abstractions;

import com.binubalan.ProjectPantherPoc.catalog.entities.Issue;
import com.binubalan.ProjectPantherPoc.catalog.entities.IssueType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IssueRepo extends JpaRepository<Issue, Long> {
    @Query("SELECT iss FROM Issue iss WHERE iss.issueType = ?1")
    List<Issue> findIssuesByType(IssueType issueType);
}
