package com.entmath.identity.domain;

import jakarta.persistence.*;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "study_goal")
public class StudyGoal {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private StudentProfile studentProfile;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Subject subject;

    @Column(nullable = false)
    private int target;

    @Column
    private LocalDate examDate;

    @Column(nullable = false)
    private int dailyMinutes;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GoalStatus status;

    @Column(nullable = false, updatable = false)
    private Instant createdAt = Instant.now();

    @Column(nullable = false)
    private Instant updatedAt = Instant.now();

    protected StudyGoal() {}

    public StudyGoal(StudentProfile studentProfile, Subject subject, int target, LocalDate examDate, int dailyMinutes, GoalStatus status) {
        this.studentProfile = studentProfile;
        this.subject = subject;
        this.target = target;
        this.examDate = examDate;
        this.dailyMinutes = dailyMinutes;
        this.status = status;
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }

    public UUID getId() { return id; }
    public StudentProfile getStudentProfile() { return studentProfile; }
    public Subject getSubject() { return subject; }
    public int getTarget() { return target; }
    public LocalDate getExamDate() { return examDate; }
    public int getDailyMinutes() { return dailyMinutes; }
    public GoalStatus getStatus() { return status; }
    public Instant getCreatedAt() { return createdAt; }
    public Instant getUpdatedAt() { return updatedAt; }

    public void setTarget(int target) {
        this.target = target;
        this.updatedAt = Instant.now();
    }

    public void setExamDate(LocalDate examDate) {
        this.examDate = examDate;
        this.updatedAt = Instant.now();
    }

    public void setDailyMinutes(int dailyMinutes) {
        this.dailyMinutes = dailyMinutes;
        this.updatedAt = Instant.now();
    }

    public void setStatus(GoalStatus status) {
        this.status = status;
        this.updatedAt = Instant.now();
    }
}
