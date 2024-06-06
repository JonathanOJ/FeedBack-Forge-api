package com.feedbackforge.feedbackforgeapi.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.feedbackforge.feedbackforgeapi.models.Task;
import com.feedbackforge.feedbackforgeapi.models.User;
import com.feedbackforge.feedbackforgeapi.models.enums.ProfileEnum;
import com.feedbackforge.feedbackforgeapi.repositories.TaskRepository;

@Service
public class TaskService {
    
    @Autowired
    private TaskRepository taskRepository;

    @Autowired  
    private UserService userService;

    
    public Task findById(Long id) {
        Optional<Task> task = this.taskRepository.findById(id);
        
        return task.orElseThrow(() -> new RuntimeException("Task not found! id: " + id));
    }

    @Transactional
    public Task save(Task task) {
        task.setId(null);
        task = this.taskRepository.save(task);
        
        return task;
    }
    
    @Transactional
    public Task update(Task task) {
        return this.taskRepository.save(task);
    }

    @Transactional
    public void delete(Long id) {
        findById(id);
        try {
            this.taskRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error deleting task! id: " + id);
        }
    }

    public Iterable<Task> findAll(Long id) {
        User user = userService.findById(id);

        if (user.getProfiles().contains(ProfileEnum.ADMIN)) {
            return this.taskRepository.findAll();
        } else {
            return this.taskRepository.findAllByUser(id);
        }
    }

    public Iterable<Task> findAllByUser(Long userId) {
        return this.taskRepository.findAllByUser(userId);
    }

}
