package com.example.SpringSecurityDemo;

import com.example.SpringSecurityDemo.Service.UserService;
import com.example.SpringSecurityDemo.domain.Role;
import com.example.SpringSecurityDemo.domain.UserMember;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
public class SpringSecurityDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityDemoApplication.class, args);
    }
    @Bean
    CommandLineRunner run(UserService userService) {
        return args -> {
            userService.saveRole(new Role(null, "ROLE_USER"));
            userService.saveRole(new Role(null, "ROLE_MANAGER"));
            userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));
            userService.saveRole(new Role(null, "ROLE_ADMIN"));

            userService.saveUserMember(new UserMember(null, "John", "John01", "1234", new ArrayList<>()));
            userService.saveUserMember(new UserMember(null, "Michael", "Michael02", "1234", new ArrayList<>()));
            userService.saveUserMember(new UserMember(null, "Thomas", "Thomas03", "1234", new ArrayList<>()));
            userService.saveUserMember(new UserMember(null, "Robert", "Robert04", "1234", new ArrayList<>()));

            userService.addRoleToUser("John01", "ROLE_USER");
            userService.addRoleToUser("John01", "ROLE_MANAGER");
            userService.addRoleToUser("Michael02", "ROLE_MANAGER");
            userService.addRoleToUser("Thomas03", "ROLE_ADMIN");
            userService.addRoleToUser("Robert04", "ROLE_USER");
            userService.addRoleToUser("Robert04", "ROLE_ADMIN");
            userService.addRoleToUser("Robert04", "ROLE_SUPER_ADMIN");
        };
    }
}
