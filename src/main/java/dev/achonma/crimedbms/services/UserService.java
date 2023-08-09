package dev.achonma.crimedbms.services;

import java.util.List;
import java.util.Optional;

import dev.achonma.crimedbms.models.User;

public interface UserService {
    public void save(User user);

    public User findByUsername(String un);

    public User getCurrentUser();

    List<User> get();

    void deleteById(Long id);

    Optional<User> findById(Long id);
}
