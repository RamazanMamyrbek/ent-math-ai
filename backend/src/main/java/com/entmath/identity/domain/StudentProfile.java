package com.entmath.identity.domain;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "student_profile")
public class StudentProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private UserAccount userAccount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Language preferredLanguage;

    @Column
    private String grade;

    @Column
    private String timezone;

    @Column(nullable = false, updatable = false)
    private Instant createdAt = Instant.now();

    @Column(nullable = false)
    private Instant updatedAt = Instant.now();

    protected StudentProfile() {}

    public StudentProfile(UserAccount userAccount, Language preferredLanguage, String grade, String timezone) {
        this.userAccount = userAccount;
        this.preferredLanguage = preferredLanguage;
        this.grade = grade;
        this.timezone = timezone;
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }

    public UUID getId() { return id; }
    public UserAccount getUserAccount() { return userAccount; }
    public Language getPreferredLanguage() { return preferredLanguage; }
    public String getGrade() { return grade; }
    public String getTimezone() { return timezone; }
    public Instant getCreatedAt() { return createdAt; }
    public Instant getUpdatedAt() { return updatedAt; }

    public void setPreferredLanguage(Language preferredLanguage) {
        this.preferredLanguage = preferredLanguage;
        this.updatedAt = Instant.now();
    }

    public void setGrade(String grade) {
        this.grade = grade;
        this.updatedAt = Instant.now();
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
        this.updatedAt = Instant.now();
    }
}
