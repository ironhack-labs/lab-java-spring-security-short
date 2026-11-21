package com.ironhack.blogcms.config;

import java.security.SecureRandom;
import java.util.HexFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Security rules for the blog CMS:
 * <ul>
 *   <li>every {@code GET} under {@code /api/**} is public;</li>
 *   <li>every other request requires the {@code ADMIN} role (HTTP Basic).</li>
 * </ul>
 * Two in-memory accounts exist: an admin ({@code ROLE_ADMIN}) and a reader ({@code ROLE_USER}),
 * the second one only to show that being logged in is not enough to write.
 * Passwords come from environment variables; when one is not set, a random password is
 * generated at startup and printed to the log, so no credential is stored in the repository.
 */
@Configuration
public class SecurityConfig {

    public static final String ADMIN = "ADMIN";
    public static final String USER = "USER";

    private static final Logger log = LoggerFactory.getLogger(SecurityConfig.class);

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Stateless REST API authenticated with HTTP Basic: no session cookie, so no CSRF exposure.
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET, "/api/**").permitAll()
                        // Lets Spring render 404/500 error bodies for public requests.
                        .requestMatchers("/error").permitAll()
                        .anyRequest().hasRole(ADMIN))
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // bcrypt by default, with the {id} prefix so the algorithm can change later.
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(
            PasswordEncoder passwordEncoder,
            @Value("${blog.security.admin.username}") String adminUsername,
            @Value("${blog.security.admin.password:}") String adminPassword,
            @Value("${blog.security.reader.username}") String readerUsername,
            @Value("${blog.security.reader.password:}") String readerPassword) {

        UserDetails admin = User.withUsername(adminUsername)
                .password(passwordEncoder.encode(resolvePassword(adminUsername, adminPassword, "BLOG_ADMIN_PASSWORD")))
                .roles(ADMIN)
                .build();
        UserDetails reader = User.withUsername(readerUsername)
                .password(passwordEncoder.encode(resolvePassword(readerUsername, readerPassword, "BLOG_READER_PASSWORD")))
                .roles(USER)
                .build();
        return new InMemoryUserDetailsManager(admin, reader);
    }

    private static String resolvePassword(String username, String configured, String envVar) {
        if (configured != null && !configured.isBlank()) {
            return configured;
        }
        byte[] bytes = new byte[12];
        new SecureRandom().nextBytes(bytes);
        String generated = HexFormat.of().formatHex(bytes);
        log.warn("No password configured for user '{}'. Generated one for this run: {} (set {} to choose your own)",
                username, generated, envVar);
        return generated;
    }
}
