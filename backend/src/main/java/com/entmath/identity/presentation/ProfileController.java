package com.entmath.identity.presentation;

import com.entmath.identity.application.ProfileService;
import com.entmath.identity.domain.Subject;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/me")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping
    public ResponseEntity<?> getProfileAndGoal(Authentication authentication) {
        UUID userId = UUID.fromString(authentication.getName());

        var profileOpt = profileService.getProfileByUserId(userId);
        if (profileOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var profile = profileOpt.get();
        ProfileResponse profileResponse = new ProfileResponse(profile.getId(), profile.getPreferredLanguage(), profile.getGrade(), profile.getTimezone());

        return ResponseEntity.ok(profileResponse);
    }

    @PatchMapping("/profile")
    public ResponseEntity<ProfileResponse> updateProfile(@RequestBody ProfileRequest request, Authentication authentication) {
        UUID userId = UUID.fromString(authentication.getName());
        var profile = profileService.upsertProfile(userId, request.preferredLanguage(), request.grade(), request.timezone());
        return ResponseEntity.ok(new ProfileResponse(profile.getId(), profile.getPreferredLanguage(), profile.getGrade(), profile.getTimezone()));
    }

    @GetMapping("/goal")
    public ResponseEntity<GoalResponse> getGoal(Authentication authentication) {
        UUID userId = UUID.fromString(authentication.getName());
        return profileService.getActiveGoalByUserId(userId, Subject.MATHEMATICS)
                .map(goal -> ResponseEntity.ok(new GoalResponse(goal.getId(), goal.getSubject(), goal.getTarget(), goal.getExamDate(), goal.getDailyMinutes(), goal.getStatus())))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/goal")
    public ResponseEntity<GoalResponse> updateGoal(@RequestBody GoalRequest request, Authentication authentication) {
        UUID userId = UUID.fromString(authentication.getName());
        var goal = profileService.upsertGoal(userId, request.subject(), request.target(), request.examDate(), request.dailyMinutes());
        return ResponseEntity.ok(new GoalResponse(goal.getId(), goal.getSubject(), goal.getTarget(), goal.getExamDate(), goal.getDailyMinutes(), goal.getStatus()));
    }
}
