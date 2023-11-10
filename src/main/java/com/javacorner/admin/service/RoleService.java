package com.javacorner.admin.service;

import com.javacorner.admin.entity.Role;

public interface RoleService {

    Role loadRoleByName(String roleName);

    Role createRole(String roleName);
}
