package com.example.taskplanner.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(nullable = false)
    private LocalDate deadline;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assigned_user_id")
    private User assignedUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Enumerated(EnumType.STRING)
    private AdminPriority adminPriority;

    // Getters and setters

    public AdminPriority getAdminPriority() {
        return adminPriority;
    }

    public void setAdminPriority(AdminPriority adminPriority) {
        this.adminPriority = adminPriority;
    }
}

enum Priority {
    HIGH, MEDIUM, LOW
}

enum Status {
    PENDING, IN_PROGRESS, COMPLETED
}

enum AdminPriority {
    URGENT, IMPORTANT, NORMAL
}

