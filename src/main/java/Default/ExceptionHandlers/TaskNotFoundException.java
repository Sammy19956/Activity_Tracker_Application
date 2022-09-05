package Default.ExceptionHandlers;

import java.io.Serial;

public class TaskNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public TaskNotFoundException(Long id){
        super(String.format("Task with id %d not found", id));
    }
}
