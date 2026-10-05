package com.entmath;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;

class ModulithVerificationTest {

    @Test
    void verifyModulith() {
        ApplicationModules.of(EntMathBackendApplication.class).verify();
    }
}
