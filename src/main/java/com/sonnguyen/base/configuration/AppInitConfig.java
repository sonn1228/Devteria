package com.sonnguyen.base.configuration;

import com.sonnguyen.base.model.User;
import com.sonnguyen.base.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.Set;

@Configuration
@RequiredArgsConstructor
public class AppInitConfig {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner createAdminAccount() {
        return args -> {
            if (userRepository.findByUsername("admin") == null) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin"));
                admin.setFirstName("Admin");
                admin.setLastName("User");
                admin.setDob(LocalDate.of(1990, 1, 1));
                admin.setRoles(Set.of("ADMIN", "USER"));
                userRepository.save(admin);
                System.out.println("Admin created");
            }
        };
    }
}
