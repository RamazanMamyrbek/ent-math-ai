package com.entmath.identity.application;

import com.entmath.identity.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.UUID;
import java.util.Optional;

@Service
public class ProfileService {

    private final UserAccountRepository userAccountRepository;
    private final StudentProfileRepository studentProfileRepository;
    private final StudyGoalRepository studyGoalRepository;

    public ProfileService(UserAccountRepository userAccountRepository,
                          StudentProfileRepository studentProfileRepository,
                          StudyGoalRepository studyGoalRepository) {
        this.userAccountRepository = userAccountRepository;
        this.studentProfileRepository = studentProfileRepository;
        this.studyGoalRepository = studyGoalRepository;
    }

    @Transactional
    public StudentProfile upsertProfile(UUID userId, Language preferredLanguage, String grade, String timezone) {
        UserAccount account = userAccountRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User account not found"));

        return studentProfileRepository.findByUserAccountId(userId)
                .map(profile -> {
                    profile.setPreferredLanguage(preferredLanguage);
                    profile.setGrade(grade);
                    profile.setTimezone(timezone);
                    return studentProfileRepository.save(profile);
                })
                .orElseGet(() -> {
                    StudentProfile newProfile = new StudentProfile(account, preferredLanguage, grade, timezone);
                    return studentProfileRepository.save(newProfile);
                });
    }

    @Transactional(readOnly = true)
    public Optional<StudentProfile> getProfileByUserId(UUID userId) {
        return studentProfileRepository.findByUserAccountId(userId);
    }

    @Transactional
    public StudyGoal upsertGoal(UUID userId, Subject subject, int target, LocalDate examDate, int dailyMinutes) {
        StudentProfile profile = studentProfileRepository.findByUserAccountId(userId)
                .orElseThrow(() -> new IllegalArgumentException("Student profile not found. Must complete profile first."));

        return studyGoalRepository.findByStudentProfileIdAndStatusAndSubject(profile.getId(), GoalStatus.ACTIVE, subject)
                .map(goal -> {
                    goal.setTarget(target);
                    goal.setExamDate(examDate);
                    goal.setDailyMinutes(dailyMinutes);
                    return studyGoalRepository.save(goal);
                })
                .orElseGet(() -> {
                    StudyGoal newGoal = new StudyGoal(profile, subject, target, examDate, dailyMinutes, GoalStatus.ACTIVE);
                    return studyGoalRepository.save(newGoal);
                });
    }

    @Transactional(readOnly = true)
    public Optional<StudyGoal> getActiveGoalByUserId(UUID userId, Subject subject) {
        return studentProfileRepository.findByUserAccountId(userId)
                .flatMap(profile -> studyGoalRepository.findByStudentProfileIdAndStatusAndSubject(profile.getId(), GoalStatus.ACTIVE, subject));
    }
}
