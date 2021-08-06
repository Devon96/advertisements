package com.advertisements.advertisements.repository;

import com.advertisements.advertisements.model.Advertisement;
import com.advertisements.advertisements.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * User JPA Repository interface
 */

public interface UserRepository extends JpaRepository<User, String> {

    User findByEmail(String email);

}
