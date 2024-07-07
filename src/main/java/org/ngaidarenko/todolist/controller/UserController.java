package org.ngaidarenko.todolist.controller;

import org.ngaidarenko.todolist.entity.Task;
import org.ngaidarenko.todolist.entity.User;
import org.ngaidarenko.todolist.request.TaskRequest;
import org.ngaidarenko.todolist.request.UserRequest;
import org.ngaidarenko.todolist.service.service.TaskService;
import org.ngaidarenko.todolist.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(name = "/users")
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
    public User getUserById(@PathVariable int userId) {
        return userService.getUserById(userId);
    }


    @PostMapping("/addUser")
    public void createUser(@RequestBody UserRequest userRequest){
        User user = new User();
        user.setName(userRequest.getName());
        user.setPassword(userRequest.getPassword());

        userService.createUser(user);
    }

    @DeleteMapping("/delete/{userId}")
    public void deleteUser(@PathVariable int userId) {
        userService.deleteUser(userId);
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
