package org.ngaidarenko.todolist.controller;

import org.ngaidarenko.todolist.entity.Task;
import org.ngaidarenko.todolist.entity.User;
import org.ngaidarenko.todolist.request.TaskRequest;
import org.ngaidarenko.todolist.request.UserRequest;
import org.ngaidarenko.todolist.service.service.TaskService;
import org.ngaidarenko.todolist.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private TaskService taskService;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable int userId) {
        return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK);
//        return userService.getUserById(userId);
    }


    @PostMapping("/addUser")
    public ResponseEntity<Void> createUser(@RequestBody UserRequest userRequest){
        User user = new User();
        user.setName(userRequest.getName());
        user.setPassword(userRequest.getPassword());

        userService.createUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable int userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{userId}/tasks")
    public List<Task> getAllTasks(@PathVariable int userId) {
        User user = userService.getUserById(userId);
        return user.getTask();
    }

    @PostMapping("/{userId}/task")
    public void createNewTask(@RequestBody TaskRequest taskRequest,
                              @PathVariable int userId) {
        User user = userService.getUserById(userId);

        Task task = new Task();
        task.setTitle(taskRequest.getTitle());
        task.setDescription(taskRequest.getDescription());
        task.setCompleted(taskRequest.getCompleted());

        user.getTask().add(task);
        userService.createUser(user);
    }

    @PostMapping("/user/{taskId}")
    public void setTaskCompleted(@PathVariable int taskId) {
        Task task = taskService.getTaskById(taskId);
        task.setCompleted(Boolean.TRUE);
        taskService.setTaskComplete(task);
    }

    @DeleteMapping("/user/{tasksId}")
    public void deleteTask(@PathVariable int tasksId) {
        taskService.deleteTaskById(tasksId);
    }

}
