package Default.Service.Impl;

import Default.DTO.TaskDTO;
import Default.Enums.Status;
import Default.Model.Task;
import Default.Service.TaskService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("AnotherService")
public class AnotherTaskServImpl implements TaskService {

    @Override
    public Task addTask(TaskDTO taskDTO) {
        return null;
    }

    @Override
    public Task findTaskById(Long id) {
        return null;
    }

    @Override
    public Task updateTask(Task task) {
        return null;
    }

    @Override
    public void deleteTask(Task task) {

    }

    @Override
    public List<Task> findTaskByStatus(Status status) {
        return null;
    }

    @Override
    public List<Task> findAllTasks() {
        return null;
    }
}
