package org.zerhusen.ams.repository.security;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerhusen.ams.model.security.User;




public interface UserRepository extends JpaRepository<User, Long> {
    User findByMobilenumber(String mobilenumber);
    
    User findByEmail(String email);
    
    User findById(long id);
}
