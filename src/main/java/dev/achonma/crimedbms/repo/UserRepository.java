package dev.achonma.crimedbms.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.achonma.crimedbms.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User findByFirstnameAndLastname(String firstname, String lastname);

    List<User> findAll();

    void deleteById(Long id);

    Optional<User> findById(Long id);

}
