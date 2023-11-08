package com.example.SpringSecurityDemo;

import com.example.SpringSecurityDemo.Service.UserService;
import com.example.SpringSecurityDemo.domain.Role;
import com.example.SpringSecurityDemo.domain.UserMember;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class SpringSecurityDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityDemoApplication.class, args);
	}


	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}



	@Bean
	CommandLineRunner run(UserService userService){
		return args -> {
		userService.saveRole(new Role(null,"ROLE_USER"));
		userService.saveRole(new Role(null,"ROLE_MANAGER"));
		userService.saveRole(new Role(null,"ROLE_SUPER_ADMIN"));
		userService.saveRole(new Role(null,"ROLE_ADMIN"));

		userService.saveUserMember(new UserMember(null,"Kajto1","kajti1","1234",new ArrayList<>()));
		userService.saveUserMember(new UserMember(null,"Kajto2","kajti2","1234",new ArrayList<>()));
		userService.saveUserMember(new UserMember(null,"Kajto3","kajti3","1234",new ArrayList<>()));
		userService.saveUserMember(new UserMember(null,"Kajtenstein","Empata","1234",new ArrayList<>()));

		userService.addRoleToUser("kajti1","ROLE_USER");
		userService.addRoleToUser("kajti1","ROLE_MANAGER");
		userService.addRoleToUser("kajti2","ROLE_MANAGER");
		userService.addRoleToUser("kajti3","ROLE_ADMIN");
		userService.addRoleToUser("Empata","ROLE_USER");
		userService.addRoleToUser("Empata","ROLE_ADMIN");
		userService.addRoleToUser("Empata","ROLE_SUPER_ADMIN");


		};
	}



}
