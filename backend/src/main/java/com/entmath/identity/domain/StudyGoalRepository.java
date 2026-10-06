package com.entmath.identity.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;
import java.util.List;

public interface StudyGoalRepository extends JpaRepository<StudyGoal, UUID> {
    List<StudyGoal> findByStudentProfileId(UUID studentProfileId);
    Optional<StudyGoal> findByStudentProfileIdAndStatusAndSubject(UUID studentProfileId, GoalStatus status, Subject subject);
}
