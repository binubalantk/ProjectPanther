package com.binubalan.ProjectPantherPoc.catalog.entities;

import com.binubalan.ProjectPantherPoc.catalog.models.IssueModel;
import jakarta.persistence.*;
import lombok.*;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table
public class Issue {
    @SequenceGenerator(
        name = "issue_sequence",
        sequenceName = "issue_sequence"
    )
    @GeneratedValue(
        generator = "issue_sequence",
        strategy = GenerationType.SEQUENCE
    )
    @Id
    private long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private LocalDateTime createdOn;

    @Column(nullable = false)
    private LocalDateTime lastUpdated;

    @ManyToOne(fetch = FetchType.LAZY)
    private Issue parent;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
        nullable = false
    )
    private IssueType issueType;

    @Column(nullable = false)
    private boolean isEnabled;

    public IssueModel toDto() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return new IssueModel(
                this.id,
                this.title,
                this.description,
                dateFormatter.format(this.createdOn),
                dateFormatter.format(this.lastUpdated),
                (this.parent != null) ? this.parent.toDto() : null,
                null,
                this.issueType,
                this.isEnabled);
    }
}
