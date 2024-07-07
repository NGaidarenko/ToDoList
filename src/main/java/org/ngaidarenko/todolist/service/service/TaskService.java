package org.ngaidarenko.todolist.service.service;

import org.ngaidarenko.todolist.entity.Task;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TaskService {
    Task getTaskById(int id);
    void setTaskComplete(Task task);
    void deleteTaskById(int id);
}
