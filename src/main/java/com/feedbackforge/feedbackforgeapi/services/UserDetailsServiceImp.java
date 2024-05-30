package com.feedbackforge.feedbackforgeapi.services;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.feedbackforge.feedbackforgeapi.models.User;
import com.feedbackforge.feedbackforgeapi.security.UserSpringSecurity;


@Service
public class UserDetailsServiceImp implements UserDetailsService{

    @Autowired
    @Lazy
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findUserByEmail(username);
        
        if(Objects.isNull(user)){
           throw new UsernameNotFoundException("User not found: " + username);
        }
        
        return new UserSpringSecurity(user.getId(), user.getEmail(), user.getPassword(), user.getProfiles());
    }
    
}
