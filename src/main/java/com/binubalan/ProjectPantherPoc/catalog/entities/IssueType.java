package com.binubalan.ProjectPantherPoc.catalog.entities;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "issue_type")
public class IssueType {
    @SequenceGenerator(
        name = "issue_type_sequence",
        sequenceName = "issue_type_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "issue_type_sequence"
    )
    @Id
    private long id;
    private String name;
    public IssueType(String name){
        this.name = name;
    }
}
