package com.cs.authservice.config;

import com.cs.authservice.entity.Role;
import com.cs.authservice.entity.User;
import com.cs.authservice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {
            // Create admin user
            User admin = User.builder()
                    .username("admin")
                    .email("admin@hospital.com")
                    .password(passwordEncoder.encode("admin123"))
                    .role(Role.ADMIN)
                    .build();

            // Create sample doctor
            User doctor = User.builder()
                    .username("doctor1")
                    .email("doctor1@hospital.com")
                    .password(passwordEncoder.encode("doctor123"))
                    .role(Role.DOCTOR)
                    .build();

            // Create sample patient
            User patient = User.builder()
                    .username("patient1")
                    .email("patient1@hospital.com")
                    .password(passwordEncoder.encode("patient123"))
                    .role(Role.PATIENT)
                    .build();

            userRepository.save(admin);
            userRepository.save(doctor);
            userRepository.save(patient);

            log.info("Sample users created:");
            log.info("Admin - username: admin, password: admin123");
            log.info("Doctor - username: doctor1, password: doctor123");
            log.info("Patient - username: patient1, password: patient123");
        }
    }
}