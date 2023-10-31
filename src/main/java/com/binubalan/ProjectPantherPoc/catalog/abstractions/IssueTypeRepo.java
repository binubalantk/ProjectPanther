package com.binubalan.ProjectPantherPoc.catalog.abstractions;

import com.binubalan.ProjectPantherPoc.catalog.entities.IssueType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IssueTypeRepo extends JpaRepository<IssueType, Long> {
    Optional<IssueType> findIssueTypeByName (String name);
}
