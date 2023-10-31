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
public class IssueRequestModel {
    private long id;
    private String title;
    private String description;
    private long issueTypeId;
    private boolean isEnabled;

    public IssueRequestModel(String title, String description, long issueTypeId, boolean isEnabled) {
        this.title = title;
        this.description = description;
        this.issueTypeId = issueTypeId;
        this.isEnabled = isEnabled;
    }

    public Issue toEntity(){
        return new Issue(
            this.id,
            this.title,
            this.description,
            null,
            null,
            null,
            null,
            this.isEnabled
        );
    }
}
