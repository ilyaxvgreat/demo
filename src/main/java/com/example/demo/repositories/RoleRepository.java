package com.example.demo.repositories;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {
    List<Role> getRolesByUsers(User user);
}
