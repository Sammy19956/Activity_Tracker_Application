package Default.Controller;

import Default.ExceptionHandlers.TaskNotFoundException;
import Default.ExceptionHandlers.UserNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController{

    @ExceptionHandler(UserNotFoundException.class)
    public String userNotFoundException(UserNotFoundException ex, Model model){
        model.addAttribute("statuscode", "404");
        model.addAttribute("title", "User Not Found");
        model.addAttribute("message", ex.getMessage());
        return "error";
    }

    @ExceptionHandler(TaskNotFoundException.class)
    public String taskNotFoundException(TaskNotFoundException tx, Model model){
            model.addAttribute("statuscode", "404");
            model.addAttribute("title", "Task Not Found");
            model.addAttribute("message", tx.getMessage());
            return "error";

    }
}
