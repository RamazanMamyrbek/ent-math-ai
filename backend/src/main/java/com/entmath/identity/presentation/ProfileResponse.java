package com.entmath.identity.presentation;

import com.entmath.identity.domain.Language;
import java.util.UUID;

public record ProfileResponse(UUID id, Language preferredLanguage, String grade, String timezone) {}
