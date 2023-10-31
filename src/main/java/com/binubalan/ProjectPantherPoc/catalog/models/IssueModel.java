package com.binubalan.ProjectPantherPoc.catalog.models;

import com.binubalan.ProjectPantherPoc.catalog.entities.Issue;
import com.binubalan.ProjectPantherPoc.catalog.entities.IssueType;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class IssueModel {
    private long id;
    private String title;
    private String description;
    private String createdOn;
    private String lastUpdated;
    private IssueModel parent;
    private List<IssueModel> children;
    private IssueType issueType;
    private boolean isEnabled;

    public IssueModel(String title, String description, String createdOn, String lastUpdated,IssueModel parent,
                      List<IssueModel> children, IssueType issueType, boolean isEnabled) {
        this.title = title;
        this.description = description;
        this.createdOn = createdOn;
        this.lastUpdated = lastUpdated;
        this.parent = parent;
        this.children = children;
        this.issueType = issueType;
        this.isEnabled = isEnabled;
    }

    public Issue toEntity(){

        return new Issue(
            this.id,
            this.title,
            this.description,
            LocalDateTime.parse(this.createdOn),
            this.lastUpdated == null ? null : LocalDateTime.parse( this.lastUpdated),
            (this.parent != null) ? this.parent.toEntity() : null,
            this.issueType,
            this.isEnabled
        );
    }
}
