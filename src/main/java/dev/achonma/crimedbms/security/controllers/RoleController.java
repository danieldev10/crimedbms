package dev.achonma.crimedbms.security.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import dev.achonma.crimedbms.models.User;
import dev.achonma.crimedbms.security.models.Role;
import dev.achonma.crimedbms.security.services.RoleService;
import dev.achonma.crimedbms.services.UserService;

@Controller
public class RoleController {
    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @GetMapping("/roles")
    public String findAll(Model model) {
        model.addAttribute("roles", roleService.findAll());
        return "roles";
    }

    @RequestMapping("/roles/findById")
    @ResponseBody
    public Optional<Role> findById(Long id) {
        return roleService.findById(id);
    }

    @PostMapping(value = "/roles/addNew")
    public String addNew(Role role) {
        roleService.save(role);
        return "redirect:/roles";
    }

    @RequestMapping(value = "/roles/update", method = { RequestMethod.PUT, RequestMethod.GET })
    public String update(Role role) {
        roleService.save(role);
        return "redirect:/roles";
    }

    @RequestMapping(value = "/roles/delete", method = { RequestMethod.DELETE, RequestMethod.GET })
    public String delete(Long id) {
        roleService.delete(id);
        return "redirect:/roles";
    }

    @GetMapping("/security/user/Edit/{id}")
    public String editEmployee(@PathVariable Long id, Model model) {
        User user = userService.findById(id).orElse(null);
        model.addAttribute("user", user);
        model.addAttribute("userRoles", roleService.getUserRoles(user));
        model.addAttribute("userNotRoles", roleService.getUserNotRoles(user));
        return "/edituser";
    }

    @RequestMapping("/security/role/assign/{userId}/{roleId}")
    public String assignRole(@PathVariable Long userId,
            @PathVariable Long roleId) {
        roleService.assignUserRole(userId, roleId);
        return "redirect:/security/user/Edit/" + userId;
    }

    @RequestMapping("/security/role/unassign/{userId}/{roleId}")
    public String unassignRole(@PathVariable Long userId,
            @PathVariable Long roleId) {
        roleService.unassignedRole(userId, roleId);
        return "redirect:/security/user/Edit/" + userId;
    }
}
