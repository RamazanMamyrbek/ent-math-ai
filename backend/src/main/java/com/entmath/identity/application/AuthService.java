package com.entmath.identity.application;

import com.entmath.identity.domain.AccountStatus;
import com.entmath.identity.domain.UserAccount;
import com.entmath.identity.domain.UserAccountRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    private final UserAccountRepository userAccountRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserAccountRepository userAccountRepository, PasswordEncoder passwordEncoder) {
        this.userAccountRepository = userAccountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UserAccount registerUser(String email, String rawPassword) {
        String normalizedEmail = email.trim().toLowerCase();

        if (userAccountRepository.existsByEmail(normalizedEmail)) {
            throw new IllegalArgumentException("Email already in use");
        }

        String passwordHash = passwordEncoder.encode(rawPassword);
        UserAccount newUser = new UserAccount(normalizedEmail, passwordHash, AccountStatus.ACTIVE);
        
        return userAccountRepository.save(newUser);
    }
}
