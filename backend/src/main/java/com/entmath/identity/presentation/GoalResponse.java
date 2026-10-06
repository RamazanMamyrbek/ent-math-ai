package com.entmath.identity.presentation;

import com.entmath.identity.domain.Subject;
import com.entmath.identity.domain.GoalStatus;
import java.time.LocalDate;
import java.util.UUID;

public record GoalResponse(UUID id, Subject subject, int target, LocalDate examDate, int dailyMinutes, GoalStatus status) {}
