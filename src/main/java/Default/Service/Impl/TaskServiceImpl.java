package Default.Service.Impl;

import java.util.*;
import Default.DTO.TaskDTO;
import Default.Enums.Status;
import Default.Model.Task;
import Default.Repositories.TaskRepository;
import Default.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@Primary
public class TaskServiceImpl implements TaskService {
    @Autowired
    TaskRepository taskRepository;

    @Override
    public Task addTask(TaskDTO taskDTO) {

        Task task = new Task();
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setStatus(Status.PENDING);
        task.setCreatedAt(LocalDateTime.now());

        return taskRepository.save(task);
    }

    @Override
    public Task findTaskById(Long id) {
        return taskRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public Task updateTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public void deleteTask(Task task) {
         taskRepository.delete(task);
    }

    @Override
    public List<Task> findTaskByStatus(Status status) {
        return taskRepository.findTaskByStatus(status);
    }

    @Override
    public List<Task> findAllTasks() {
        return taskRepository.findAll();
    }






}
