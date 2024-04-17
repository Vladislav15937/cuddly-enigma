package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.repositoryes.RoleRepositry;

import java.util.List;

public interface RoleService {

    List<Role> getAllRoles();
    Role getRoleById(Long id);
}
