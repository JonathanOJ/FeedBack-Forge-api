package com.feedbackforge.feedbackforgeapi.controller;

import org.springframework.web.bind.annotation.RestController;

import com.feedbackforge.feedbackforgeapi.models.Task;
import com.feedbackforge.feedbackforgeapi.services.TaskService;

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
@RequestMapping("/task")
@Validated
public class TaskController {

    @Autowired
    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/findAll")
    public List<Task> findAll() {
        return (List<Task>) this.taskService.findAll();
    }

    @GetMapping("/{id}")
    public  ResponseEntity<Task> findById(@PathVariable Long id) {
        Task task = this.taskService.findById(id);
        return ResponseEntity.ok().body(task);
    }

    @GetMapping("/findAllByUser/{userId}")
    public  Iterable<Task> findAllByUser(@PathVariable Long userId) {
        Iterable<Task> task = this.taskService.findAllByUser(userId);
        return task;
    }

    @PostMapping("/save")
    public Task save(@RequestBody Task task) {
        return this.taskService.save(task);
    }

    @PostMapping("/update")
    public Task update(@RequestBody Task task) {
        return this.taskService.update(task);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        this.taskService.delete(id);
    }

}
