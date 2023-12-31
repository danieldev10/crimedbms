package dev.achonma.crimedbms.security.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.achonma.crimedbms.models.User;
import dev.achonma.crimedbms.repo.UserRepository;
import dev.achonma.crimedbms.security.models.Role;
import dev.achonma.crimedbms.security.repositories.RoleRepository;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Role> findAll() {
        return roleRepository.findAll();
    };

    public Optional<Role> findById(Long id) {
        return roleRepository.findById(id);
    }

    public void delete(Long id) {
        roleRepository.deleteById(id);
    }

    public void save(Role role) {
        roleRepository.save(role);
    }

    public void assignUserRole(Long userId, Long roleId) {
        User user = userRepository.findById(userId).orElse(null);
        Role role = roleRepository.findById(roleId).orElse(null);

        Set<Role> userRoles = user.getRoles();
        userRoles.add(role);
        user.setRoles(userRoles);

        userRepository.save(user);
    }

    public void unassignedRole(Long userId, Long roleId) {
        User user = userRepository.findById(userId).orElse(null);
        Set<Role> userRoles = user.getRoles();

        userRoles.removeIf(x -> x.getId() == roleId);
        userRepository.save(user);
    }

    public Set<Role> getUserRoles(User user) {
        return user.getRoles();
    }

    public Set<Role> getUserNotRoles(User user) {
        return roleRepository.getUserNotRoles(user.getId());
    }
}
