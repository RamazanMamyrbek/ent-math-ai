package com.entmath.identity.presentation;

import java.util.UUID;

public record SessionResponse(boolean authenticated, UUID userId, String email) {}
