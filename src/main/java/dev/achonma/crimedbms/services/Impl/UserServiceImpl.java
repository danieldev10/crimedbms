package dev.achonma.crimedbms.services.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import dev.achonma.crimedbms.models.User;
import dev.achonma.crimedbms.repo.UserRepository;
import dev.achonma.crimedbms.services.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void save(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String un) {
        return userRepository.findByUsername(un);
    }

    @Override
    public User getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> get() {
        return userRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

}
