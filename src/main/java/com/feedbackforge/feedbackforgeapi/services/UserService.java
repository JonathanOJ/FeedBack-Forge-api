package com.feedbackforge.feedbackforgeapi.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.feedbackforge.feedbackforgeapi.models.User;
import com.feedbackforge.feedbackforgeapi.repositories.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    // @Autowired
    // private ArticleRepository articleRepository;

    
    public User findById(Long id) {
        Optional<User> user = this.userRepository.findById(id);
        
        return user.orElseThrow(() -> new RuntimeException("User not found! id: " + id));
    }
    
    @Transactional
    public User save(User user) {
        System.out.println(user);
        user.setId(null);
        user = this.userRepository.save(user);
        
        return user;
    }
    
    @Transactional
    public User update(User user) {
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

    public User login(User user) {
        Optional<User> findUser = Optional.ofNullable(findUserByEmail(user.getEmail()));

        if (!findUser.isPresent() || !findUser.get().getPassword().equals(user.getPassword())) {
            throw new RuntimeException("Invalid email or password!");
        }

        return findUser.orElseThrow(() -> new RuntimeException("User not found!"));
    }
}
