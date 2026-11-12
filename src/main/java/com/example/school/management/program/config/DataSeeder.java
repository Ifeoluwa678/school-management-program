package com.example.school.management.program.config;

import com.example.school.management.program.model.Admin;
import com.example.school.management.program.model.Role;
import com.example.school.management.program.model.User;
import com.example.school.management.program.repository.AdminRepository;
import com.example.school.management.program.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {

    private final UserService userService;
    private final AdminRepository adminRepository;

    @Value("${app.admin.username}")
    private String adminUsername;

    @Value("${app.admin.password}")
    private String adminPassword;

    @Value("${app.admin.first-name}")
    private String adminFirstName;

    @Value("${app.admin.surname}")
    private String adminSurname;

    public DataSeeder(UserService userService, AdminRepository adminRepository) {
        this.userService = userService;
        this.adminRepository = adminRepository;
    }

    @Override
    public void run(String... args) {
        if (userService.findByUsername(adminUsername).isEmpty()) {
            User adminUser = userService.registerUser(adminUsername, adminPassword, Role.ADMIN);

            Admin adminProfile = new Admin();
            adminProfile.setUser(adminUser);
            adminProfile.setFirstName(adminFirstName);
            adminProfile.setSurname(adminSurname);

            adminRepository.save(adminProfile);
            System.out.println("Default Admin account created successfully: " + adminUsername);
        }
    }
}