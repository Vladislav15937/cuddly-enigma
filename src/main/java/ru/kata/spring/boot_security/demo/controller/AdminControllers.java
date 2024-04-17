package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.repositoryes.RoleRepositry;
import ru.kata.spring.boot_security.demo.repositoryes.UserRepository;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminControllers {

    private final UserService userService;
    private final RoleService roleService;

    public AdminControllers(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping
    public String allUsers(Model model) {
        model.addAttribute("person", userService.show());
        return "users";
    }

    @GetMapping("/add")
    public String addUser(@ModelAttribute("person") @Validated User user, Model model) {

        List<Role> roles = roleService.getAllRoles();
        model.addAttribute("roles", roles);
        return "addUser";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("person") User user) {

        userService.save(user);
        return "redirect:/admin";
    }

    @GetMapping("/view")
    public String show(@RequestParam("id") Long id, Model model) {
        User user = userService.getUserById(id).get();
        model.addAttribute("views", user);
        return "viewForAdmin";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.delete(id);
        return "redirect:/admin";
    }

    @GetMapping("/update")
    public String updateUser(@RequestParam("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id).get());
        List<Role> roles = roleService.getAllRoles();
        model.addAttribute("roles", roles);
        return "updateUser";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("user") User user, @RequestParam("id") Long id) {
        userService.updateById(id, user);
        return "redirect:/admin";
    }
}
