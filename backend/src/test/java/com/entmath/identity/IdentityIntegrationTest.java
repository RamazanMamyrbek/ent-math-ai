package com.entmath.identity;

import com.entmath.identity.domain.AccountStatus;
import com.entmath.identity.domain.UserAccount;
import com.entmath.identity.domain.UserAccountRepository;
import com.entmath.identity.presentation.LoginRequest;
import com.entmath.identity.presentation.RegisterRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
public class IdentityIntegrationTest {

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15-alpine");

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
        registry.add("spring.flyway.enabled", () -> "true");
        registry.add("spring.session.jdbc.initialize-schema", () -> "never");
    }

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserAccountRepository userAccountRepository;

    @BeforeEach
    void setup() {
        userAccountRepository.deleteAll();
    }

    @Test
    void testRegisterAndLogin() {
        // Register
        RegisterRequest registerReq = new RegisterRequest("test@test.com", "password");
        ResponseEntity<String> regRes = restTemplate.postForEntity("/api/v1/auth/register", registerReq, String.class);
        assertThat(regRes.getStatusCode()).isEqualTo(HttpStatus.OK);
        
        List<String> cookies = regRes.getHeaders().get(HttpHeaders.SET_COOKIE);
        assertThat(cookies).isNotNull();
        assertThat(cookies.toString()).contains("SESSION=");
        
        // Unauthenticated access
        ResponseEntity<String> profileRes = restTemplate.getForEntity("/api/v1/me", String.class);
        assertThat(profileRes.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
        
        // Login
        LoginRequest loginReq = new LoginRequest("test@test.com", "password");
        ResponseEntity<String> loginRes = restTemplate.postForEntity("/api/v1/auth/login", loginReq, String.class);
        assertThat(loginRes.getStatusCode()).isEqualTo(HttpStatus.OK);
        
        List<String> sessionCookies = loginRes.getHeaders().get(HttpHeaders.SET_COOKIE);
        String sessionCookie = sessionCookies.stream().filter(c -> c.startsWith("SESSION=")).findFirst().orElseThrow();
        
        // Access protected resource
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.COOKIE, sessionCookie);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        ResponseEntity<String> authProfileRes = restTemplate.exchange("/api/v1/me", HttpMethod.GET, entity, String.class);
        assertThat(authProfileRes.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND); // Not created yet
    }
}
