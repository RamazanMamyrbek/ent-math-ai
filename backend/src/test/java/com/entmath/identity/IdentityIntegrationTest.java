package com.entmath.identity;

import com.entmath.identity.domain.UserAccountRepository;
import com.entmath.identity.presentation.LoginRequest;
import com.entmath.identity.presentation.RegisterRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers(disabledWithoutDocker = true)
public class IdentityIntegrationTest {

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15-alpine");

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
        registry.add("spring.flyway.url", postgres::getJdbcUrl);
        registry.add("spring.flyway.user", postgres::getUsername);
        registry.add("spring.flyway.password", postgres::getPassword);
        registry.add("spring.flyway.enabled", () -> "true");
        // Force Hibernate to create tables in case Flyway is skipped
        registry.add("spring.jpa.hibernate.ddl-auto", () -> "update");
        // Force Spring Session to create tables in case Flyway is skipped
        registry.add("spring.session.jdbc.initialize-schema", () -> "always");
    }

    @LocalServerPort
    private int port;

    @Autowired
    private UserAccountRepository userAccountRepository;

    private final HttpClient httpClient = HttpClient.newHttpClient();

    private String getBaseUrl() {
        return "http://localhost:" + port;
    }

    @BeforeEach
    void setup() {
        userAccountRepository.deleteAll();
    }

    @Test
    void testCsrfBootstrapAndRegistrationFlow() throws Exception {
        // Step 1: GET /api/v1/auth/session (mimicking initial page load) to bootstrap CSRF token
        HttpRequest sessionReq = HttpRequest.newBuilder()
                .uri(URI.create(getBaseUrl() + "/api/v1/auth/session"))
                .GET()
                .build();
        HttpResponse<String> sessionRes = httpClient.send(sessionReq, HttpResponse.BodyHandlers.ofString());
        assertThat(sessionRes.statusCode()).isEqualTo(200);
        
        List<String> initialCookies = sessionRes.headers().allValues(HttpHeaders.SET_COOKIE);
        assertThat(initialCookies).isNotEmpty();
        String xsrfToken = extractXsrfToken(initialCookies);
        assertThat(xsrfToken).isNotNull();

        // Step 2: Register user with CSRF token
        String registerJson = "{\"email\":\"testcsrf@test.com\",\"password\":\"password\"}";
        HttpRequest registerHttpReq = HttpRequest.newBuilder()
                .uri(URI.create(getBaseUrl() + "/api/v1/auth/register"))
                .header("Content-Type", "application/json")
                .header("X-XSRF-TOKEN", xsrfToken)
                .header(HttpHeaders.COOKIE, "XSRF-TOKEN=" + xsrfToken)
                .POST(HttpRequest.BodyPublishers.ofString(registerJson))
                .build();
        
        HttpResponse<String> regRes = httpClient.send(registerHttpReq, HttpResponse.BodyHandlers.ofString());
        assertThat(regRes.statusCode()).isEqualTo(200);

        List<String> regCookies = regRes.headers().allValues(HttpHeaders.SET_COOKIE);
        assertThat(regCookies).isNotEmpty();
        String sessionCookie = extractSessionCookie(regCookies);
        assertThat(sessionCookie).isNotNull();

        // Step 3: Login with CSRF token
        String loginJson = "{\"email\":\"testcsrf@test.com\",\"password\":\"password\"}";
        HttpRequest loginHttpReq = HttpRequest.newBuilder()
                .uri(URI.create(getBaseUrl() + "/api/v1/auth/login"))
                .header("Content-Type", "application/json")
                .header("X-XSRF-TOKEN", xsrfToken)
                .header(HttpHeaders.COOKIE, "XSRF-TOKEN=" + xsrfToken)
                .POST(HttpRequest.BodyPublishers.ofString(loginJson))
                .build();
        
        HttpResponse<String> loginRes = httpClient.send(loginHttpReq, HttpResponse.BodyHandlers.ofString());
        assertThat(loginRes.statusCode()).isEqualTo(200);
    }
    
    @Test
    void testRegisterWithoutCsrfFails() throws Exception {
        String registerJson = "{\"email\":\"testnocsrf@test.com\",\"password\":\"password\"}";
        HttpRequest registerHttpReq = HttpRequest.newBuilder()
                .uri(URI.create(getBaseUrl() + "/api/v1/auth/register"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(registerJson))
                .build();
        
        HttpResponse<String> regRes = httpClient.send(registerHttpReq, HttpResponse.BodyHandlers.ofString());
        assertThat(regRes.statusCode()).isEqualTo(403);
    }

    private String extractXsrfToken(List<String> cookies) {
        return cookies.stream()
                .filter(c -> c.startsWith("XSRF-TOKEN="))
                .map(c -> c.split(";")[0].substring("XSRF-TOKEN=".length()))
                .findFirst()
                .orElse(null);
    }

    private String extractSessionCookie(List<String> cookies) {
        return cookies.stream()
                .filter(c -> c.startsWith("SESSION="))
                .map(c -> c.split(";")[0])
                .findFirst()
                .orElse(null);
    }
}
