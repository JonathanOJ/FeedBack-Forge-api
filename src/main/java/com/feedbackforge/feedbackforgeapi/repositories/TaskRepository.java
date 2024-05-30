package com.feedbackforge.feedbackforgeapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.feedbackforge.feedbackforgeapi.models.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{

    @Query("FROM Task a WHERE a.user.id = :userId")
    List<Task> findAllByUser(Long userId);
}
