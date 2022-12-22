package com.icolak.entity;

import com.icolak.enums.Status;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "projects")
@Where(clause = "is_deleted=false")
public class Project extends BaseEntity{

    private String projectCode;
    private String projectName;

    @Column(columnDefinition = "DATE")
    private LocalDate startDate;

    @Column(columnDefinition = "DATE")
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    private Status projectStatus;

    @Column(columnDefinition = "text")
    private String projectDetail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    private User assignedManager;
}
