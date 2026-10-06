package com.entmath.identity.presentation;

import java.util.UUID;

public record AuthResponse(UUID userId, String email) {}
