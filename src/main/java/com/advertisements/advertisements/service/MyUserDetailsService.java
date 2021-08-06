package com.advertisements.advertisements.service;

import com.advertisements.advertisements.model.User;
import com.advertisements.advertisements.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * This class implements the UserDetailsService and retrieves user data from database
 */
@Service
public class MyUserDetailsService implements UserDetailsService {


    private UserRepository userRepository;

    @Autowired
    public MyUserDetailsService (UserRepository userRepository){
        this.userRepository = userRepository;
    }

    /**
     * Compiles a security user object from database
     * @param email - User email address
     * @return UserDetails
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }
}