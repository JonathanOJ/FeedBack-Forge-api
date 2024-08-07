package com.feedbackforge.feedbackforgeapi.controller;

import org.springframework.web.bind.annotation.RestController;

import com.feedbackforge.feedbackforgeapi.models.User;
import com.feedbackforge.feedbackforgeapi.services.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;



@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/findAll")
    public List<User> findAll() {
        return (List<User>) this.userService.findAll();
    }

    @GetMapping("/{id}")
    public  ResponseEntity<User> findById(@PathVariable Long id) {
        User user = this.userService.findById(id);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/save")
    public User save(@RequestBody User user) {
        return this.userService.save(user);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        return userService.register(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {

       return userService.login(user);

    } 

    @PostMapping("/update")
    public User update(@RequestBody User user) {
        return this.userService.update(user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        this.userService.delete(id);
    }

    @GetMapping("/findAllByRole/{role}")
    public Iterable<User> getMethodName(@PathVariable String role) {
        return this.userService.findAllByRole(role);
    }
}
