package ru.kata.spring.rest.dao;

import ru.kata.spring.rest.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleDao {

    Role getRoleByName(String roleName);

    Set<Role> getRolesSet(Set<Role> rolesWithoutPrefix);

    List<Role> getAllRoles();
}
