package Default.Controller;

import java.util.*;
import Default.DTO.TaskDTO;
import Default.Enums.Status;
import Default.Model.Task;
import Default.Service.Impl.TaskServiceImpl;
import Default.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;

@Controller
public class TaskController {

    TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/task-form")
    public String showTaskForm(Model model){
        model.addAttribute("task", new Task());
        return "task-form";
    }

    @GetMapping("add-task")
    public String addTask(@ModelAttribute("task") TaskDTO taskDTO){
        taskService.addTask(taskDTO);
        System.out.println("The code got here");
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model){
        model.addAttribute("task", taskService.findTaskById(id));
        return "updateTask";
    }

    @PostMapping("update-task/{id}")
    public String updateTask(@PathVariable("id") Long id, Model model){
        Task task = taskService.findTaskById(id);
        task.setUpdatedAt(LocalDateTime.now());
        task.setId(id);
        taskService.updateTask(task);
        model.addAttribute("tasks", taskService.findTaskById(id));
        return "redirect:/";
    }

    @GetMapping("delete-task/{id}")
    public String deleteTask(@PathVariable("id") Long id, Model model){
        Task task = taskService.findTaskById(id);
        model.addAttribute("task", task);

        taskService.deleteTask(task);
        return "redirect:/";
    }

    @GetMapping("move-up/{id}")
    public String moveUp(@PathVariable("id") Long id, Model model){
        Task task = taskService.findTaskById(id);
        if(task.getStatus() == Status.PENDING && task.getStatus() != Status.DONE){
            task.setStatus(Status.IN_PROGRESS);
            taskService.updateTask(task);
            return "redirect:/";
        }
       else if(task.getStatus() == Status.IN_PROGRESS && task.getStatus() != Status.PENDING) {
            task.setCompletedAt(LocalDateTime.now());
            task.setStatus(Status.DONE);
            taskService.updateTask(task);
            return "redirect:/";
        }
       else{
           return "redirect:/";
        }
    }

    @GetMapping("move-down/{id}")
    public String moveDown(@PathVariable("id") Long id, Model model){
        Task task = taskService.findTaskById(id);
        if(task.getStatus() == Status.DONE){
            task.setCompletedAt(null);
            task.setStatus(Status.IN_PROGRESS);
            taskService.updateTask(task);
            return "redirect:/";
        }

        else if(task.getStatus() == Status.IN_PROGRESS){
            task.setStatus(Status.PENDING);
            taskService.updateTask(task);
            return "redirect:/";
        }
        return "redirect:/";
    }

    @GetMapping("pending")
        public String viewPendingTasks(Model model){

        List<Task> pendingTasks = new ArrayList<>();
        List<Task> allTasks = taskService.findAllTasks();
        for(Task task: allTasks){
            if(task.getStatus() == Status.PENDING){
                pendingTasks.add(task);
            }
        }
        model.addAttribute("pendingTasks", pendingTasks);

        return "taskByPending";

        }

    @GetMapping("in-progress")
    public String viewTasksInProgress(Model model){

        List<Task> progress = new ArrayList<>();
        List<Task> allTasks = taskService.findAllTasks();
        for(Task task: allTasks){
            if(task.getStatus() == Status.IN_PROGRESS){
                progress.add(task);
            }
        }
        model.addAttribute("progress", progress);

        return "in-progress";

    }

    @GetMapping("done-tasks")
    public String viewDoneTasks(Model model){

        List<Task> doneTasks = new ArrayList<>();
        List<Task> allTasks = taskService.findAllTasks();
        for(Task task: allTasks){
            if(task.getStatus() == Status.DONE){
                doneTasks.add(task);
            }
        }
        model.addAttribute("done", doneTasks);

        return "done-tasks";

    }

}

