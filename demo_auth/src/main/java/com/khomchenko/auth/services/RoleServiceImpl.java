package com.khomchenko.auth.services;

import com.khomchenko.auth.model.Authority;
import com.khomchenko.auth.model.Role;
import com.khomchenko.auth.model.User;
import com.khomchenko.auth.repositories.RoleRepository;
import com.khomchenko.auth.repositories.UserRepository;
import com.khomchenko.auth.services.exceptions.RoleNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role getAdminRole() {
        return roleRepository.findAll().stream()
                .filter(role -> role.getAuthority().equals(Authority.ROLE_ADMIN.getName()))
                .findAny().orElseThrow(RoleNotFoundException::new);
    }

    @Override
    public Role getManagerRole() {
        return roleRepository.findAll().stream()
                .filter(role -> role.getAuthority().equals(Authority.ROLE_MANAGER.getName()))
                .findAny().orElseThrow(RoleNotFoundException::new);
    }

    @Override
    public Role getDefaultRole() {
        return roleRepository.findAll().stream()
                .filter(role -> role.getAuthority().equals(Authority.ROLE_DEFAULT.getName()))
                .findAny().orElseThrow(RoleNotFoundException::new);
    }

}
