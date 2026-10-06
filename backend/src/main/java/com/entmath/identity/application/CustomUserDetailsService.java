package com.entmath.identity.application;

import com.entmath.identity.domain.UserAccount;
import com.entmath.identity.domain.UserAccountRepository;
import com.entmath.identity.domain.AccountStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserAccountRepository userAccountRepository;

    public CustomUserDetailsService(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserAccount account = userAccountRepository.findByEmail(email.toLowerCase())
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));

        if (account.getStatus() != AccountStatus.ACTIVE) {
            throw new org.springframework.security.authentication.DisabledException("Account is not active");
        }

        return new org.springframework.security.core.userdetails.User(
                account.getId().toString(), // We use the UUID as the Principal name for easy access later
                account.getPasswordHash(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_STUDENT"))
        );
    }
}
