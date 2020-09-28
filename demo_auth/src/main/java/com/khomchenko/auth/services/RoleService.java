package com.khomchenko.auth.services;


import com.khomchenko.auth.model.Role;

import java.util.List;

public interface RoleService {
    public Role getAdminRole();

    public Role getManagerRole();

    public Role getDefaultRole();

}
