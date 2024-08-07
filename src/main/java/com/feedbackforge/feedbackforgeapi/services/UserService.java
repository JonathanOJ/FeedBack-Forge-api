package com.feedbackforge.feedbackforgeapi.services;

import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.feedbackforge.feedbackforgeapi.models.LoginResponse;
import com.feedbackforge.feedbackforgeapi.models.User;
import com.feedbackforge.feedbackforgeapi.models.enums.ProfileEnum;
import com.feedbackforge.feedbackforgeapi.repositories.UserRepository;
import com.feedbackforge.feedbackforgeapi.security.JWTUtil;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private JWTUtil jwtUtil;

    public User findById(Long id) {
        Optional<User> user = this.userRepository.findById(id);
        
        return user.orElseThrow(() -> new RuntimeException("User not found! id: " + id));
    }
    
    @Transactional
    public User save(User user) {
        user.setId(null);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        if(user.getRole().equals("admin")){
            user.setProfiles(Set.of(ProfileEnum.ADMIN.getCode()));
        } else {
            user.setProfiles(Set.of(ProfileEnum.USER.getCode()));
        }
        
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

    @Transactional
    public ResponseEntity<?> register(User user) {
        try {
            Optional<User> userFound = Optional.ofNullable(this.userRepository.findUserByEmail(user.getEmail()));
    
            if (userFound.isPresent()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("E-mail já cadastrado");
            }
    
            User userSaved = save(user);
            String token = jwtUtil.generateToken(userSaved.getEmail());
    
            LoginResponse loginResponse = new LoginResponse(token, userSaved);
            loginResponse.setToken(token);
            loginResponse.setUser(userSaved);
    
            return ResponseEntity.ok().body(loginResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao registrar usuário");
        }
    }
    

    public ResponseEntity<?> login(User user) {
        try {
            Optional<User> userFoundOptional = Optional.ofNullable(this.userRepository.findUserByEmail(user.getEmail()));
            
            if (userFoundOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum usuário encontrado com o e-mail informado");
            }
    
            User userFound = userFoundOptional.get();
    
            if (!bCryptPasswordEncoder.matches(user.getPassword(), userFound.getPassword())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("E-mail ou senha inválidos");
            }
    
            String token = jwtUtil.generateToken(userFound.getEmail());
    
            LoginResponse loginResponse = new LoginResponse(token, userFound);
            loginResponse.setToken(token);
            loginResponse.setUser(userFound);
    
            return ResponseEntity.ok().body(loginResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao processar a solicitação");
        }
    }
    

}
