package ru.kata.spring.rest.service;


import ru.kata.spring.rest.model.Role;

import java.util.List;

public interface RoleService {

    List<Role> getAllRoles();
    Role getRoleByName(String roleName);
}
