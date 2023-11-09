package com.example.SpringSecurityDemo.Service;

import com.example.SpringSecurityDemo.domain.Role;
import com.example.SpringSecurityDemo.domain.UserMember;
import com.example.SpringSecurityDemo.repo.RoleRepo;
import com.example.SpringSecurityDemo.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserMember userMember = userRepo.findByUsername(username);
        if (userMember == null) {
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            log.info("User found in the database: {}", username);

        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        userMember.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
        return new org.springframework.security.core.userdetails.User(userMember.getUsername(), userMember.getPassword(), authorities);
    }

    @Override
    public UserMember saveUserMember(UserMember userMember) {
        log.info("Saving new userMember {} to the database", userMember.getName());
        userMember.setPassword(passwordEncoder.encode(userMember.getPassword()));
        return userRepo.save(userMember);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role {} to the database", role.getName());
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Adding role {} to userMember {}", roleName, username);
        UserMember userMember = userRepo.findByUsername(username);
        Role role = roleRepo.findByName(roleName);
        userMember.getRoles().add(role);
    }

    @Override
    public UserMember getUserMember(String username) {
        log.info("Fetching user {}", username);
        return userRepo.findByUsername(username);
    }

    @Override
    public List<UserMember> getUserMembers() {
        log.info("Fetching all users");
        return userRepo.findAll();
    }
}

