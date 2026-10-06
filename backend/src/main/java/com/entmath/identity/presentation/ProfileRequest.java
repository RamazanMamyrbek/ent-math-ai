package com.entmath.identity.presentation;

import com.entmath.identity.domain.Language;

public record ProfileRequest(Language preferredLanguage, String grade, String timezone) {}
