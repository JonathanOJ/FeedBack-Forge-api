package com.feedbackforge.feedbackforgeapi.services;

import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.feedbackforge.feedbackforgeapi.models.User;
import com.feedbackforge.feedbackforgeapi.models.enums.ProfileEnum;
import com.feedbackforge.feedbackforgeapi.repositories.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User findById(Long id) {
        Optional<User> user = this.userRepository.findById(id);
        
        return user.orElseThrow(() -> new RuntimeException("User not found! id: " + id));
    }
    
    @Transactional
    public User save(User user) {
        user.setId(null);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setProfiles(Set.of(ProfileEnum.USER.getCode()));
        user = this.userRepository.save(user);
        
        return user;
    }
    
    @Transactional
    public User update(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return this.userRepository.save(user);
    }

    @Transactional
    public void delete(Long id) {
        findById(id);
        try {
            this.userRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error deleting user! id: " + id);
        }
    }

    public Iterable<User> findAll() {
        return this.userRepository.findAll();
    }

    public User findUserByEmail(String email) {
        Optional<User> user = Optional.ofNullable(this.userRepository.findUserByEmail(email));
        
        
        return user.orElseThrow(() -> new RuntimeException("User not found! email: " + email));
    }

    public Iterable<User> findAllByRole(String role) {
        Optional<Iterable<User>> users = Optional.ofNullable(this.userRepository.findAllByRole(role));

        return users.orElseThrow(() -> new RuntimeException("Evaluators not found!"));
    }
}
