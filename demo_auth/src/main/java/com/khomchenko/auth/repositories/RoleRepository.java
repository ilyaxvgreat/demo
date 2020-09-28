package com.khomchenko.auth.repositories;

import com.khomchenko.auth.model.Role;
import com.khomchenko.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {
    List<Role> getRolesByUsers(User user);
}
