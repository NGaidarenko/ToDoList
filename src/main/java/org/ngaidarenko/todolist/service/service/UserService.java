package org.ngaidarenko.todolist.service.service;

import org.ngaidarenko.todolist.entity.Task;
import org.ngaidarenko.todolist.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<User> getAllUsers();

    User getUserById(int id);

    void createUser(User user);

    void deleteUser(int id);

}
