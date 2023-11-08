package com.example.SpringSecurityDemo.Service;

import com.example.SpringSecurityDemo.domain.Role;
import com.example.SpringSecurityDemo.domain.UserMember;

import java.util.List;

public interface UserService {

    UserMember saveUserMember (UserMember userMember);
    Role saveRole(Role role);

    void addRoleToUser(String username, String roleName);

    UserMember getUserMember(String username);
    List<UserMember> getUserMembers();
}

