package com.example.SpringSecurityDemo.repo;

import com.example.SpringSecurityDemo.domain.UserMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserMember, Long> {

    UserMember findByUsername(String username);

}
