package com.entmath.identity.presentation;

import com.entmath.identity.domain.Subject;
import java.time.LocalDate;

public record GoalRequest(Subject subject, int target, LocalDate examDate, int dailyMinutes) {}
