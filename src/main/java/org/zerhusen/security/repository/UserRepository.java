package org.zerhusen.security.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerhusen.model.security.User;

/**
 * Created by stephan on 20.03.16.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByMobilenumber(String mobilenumber);
    
    User findByEmail(String email);
    
    User findById(long id);
}
