package com.test.shopping.service;

import com.test.shopping.model.User;
import com.test.shopping.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                String.format("user with email '%s' not found", username)));
    }

    public User registerAppUser(User user){
        boolean userExists = userRepo.findByUsername(user.getUsername()).isPresent();
        if (userExists){
            throw new RuntimeException(
                    String.format("user with email '%s' already exists",user.getUsername())
            );
        }

        String encondedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encondedPassword);
        return userRepo.save(user);
    }

    public List<User> getAll() {
        return userRepo.findAll();
    }
}
