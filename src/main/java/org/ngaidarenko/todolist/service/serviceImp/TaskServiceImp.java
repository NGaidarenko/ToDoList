package org.ngaidarenko.todolist.service.serviceImp;

import org.ngaidarenko.todolist.entity.Task;
import org.ngaidarenko.todolist.repository.TaskRepository;
import org.ngaidarenko.todolist.service.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TaskServiceImp implements TaskService {
    @Autowired
    private TaskRepository taskRepository;


    @Override
    public Task getTaskById(int id) {
        return taskRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public void setTaskComplete(Task task) {
        taskRepository.save(task);
    }

    @Override
    public void deleteTaskById(int id) {
        taskRepository.deleteById(id);
    }
}
