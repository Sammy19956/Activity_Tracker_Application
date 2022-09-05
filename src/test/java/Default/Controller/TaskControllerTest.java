package Default.Controller;

import Default.DTO.TaskDTO;
import Default.Model.Task;
import Default.Repositories.TaskRepository;
import Default.Service.Impl.TaskServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class TaskControllerTest {

    @Mock
    TaskRepository taskRepository;

    @InjectMocks
    TaskServiceImpl taskService;


    TaskDTO taskDTO = new TaskDTO();
    Task task = new Task();

    @Test
    void addTask() {

        taskDTO.setTitle("This is my first Task");
        taskDTO.setDescription("I want to make out time for myself today");
        when(taskService.addTask(taskDTO)).thenReturn(task);

        var actual = taskService.addTask(taskDTO);
        var expected = task;
        assertEquals( expected , actual );

    }
}