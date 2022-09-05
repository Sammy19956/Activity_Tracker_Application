package Default.Service;

import java.util.*;
import Default.DTO.TaskDTO;
import Default.Enums.Status;
import Default.Model.Task;

public interface TaskService {

    Task addTask(TaskDTO taskDTO);

    Task findTaskById(Long id);

    Task updateTask(Task task);

    void deleteTask(Task task);

    List<Task> findTaskByStatus(Status status);

    List<Task> findAllTasks();
}
