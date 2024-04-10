package com.example.liquibase.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.NotFound;

import java.util.Date;


@NoArgsConstructor
@Data
@Getter
@Setter
@Entity
@AllArgsConstructor
@Table(name = "employee", uniqueConstraints = @UniqueConstraint(columnNames = {"employee_name", "phone_number"}))
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long employeeId;


    @Column(name = "employee_name", nullable = false)
    @NotBlank(message = "Employee name should be provided")
    private String employeeName;

    @Column(name = "employee_designation", nullable = false)
    @NotBlank(message = "Employee designation should be provided")
    private String employeeDesignation;

    @Column(name = "phone_number", nullable = false, length = 10)
    @Size(min = 10, max = 10,message = "Phone number should have 10 numbers")
    private String phoneNumber;

    @CreationTimestamp
    @Column(name = "created_date", nullable = false, updatable = false)
    Date createdDate;


    @Enumerated(EnumType.STRING)
    @Column(name = "current_status")
    private CampaignStatus currentStatus;

    public enum CampaignStatus {
        CAMPAIGN_CREATION_IN_PROGRESS,
        CAMPAIGN_PLANNING_IN_PROGRESS,
        SEGMENTATION_IN_PROGRESS,

        CAMPAIGN_PLAN_SUBMITTED,
        EXECUTION_IN_PROGRESS
    }

    @Column(columnDefinition = "BOOLEAN DEFAULT 'true'", name = "is_active")
    public Boolean isActive;

    private Integer rating;

    @PrePersist
    protected void onCreate() {
        createdDate = new Date();

    }
}
