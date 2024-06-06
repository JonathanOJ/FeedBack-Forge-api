package com.feedbackforge.feedbackforgeapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.feedbackforge.feedbackforgeapi.models.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{

    @Query("FROM Task a WHERE a.user.id = :userId")
    Iterable<Task> findAllByUser(Long userId);
}
