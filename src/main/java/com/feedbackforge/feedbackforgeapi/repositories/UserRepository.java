package com.feedbackforge.feedbackforgeapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.feedbackforge.feedbackforgeapi.models.User;



@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    @Query("FROM User u WHERE u.email = :email")
    public User findUserByEmail(String email);

    @Query("FROM User u WHERE u.role = :role")
    public Iterable<User> findAllByRole(String role);

}
