package Default.ExceptionHandlers;

import java.io.Serial;

public class UserNotFoundException extends  RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    public UserNotFoundException(Long id){
        super(String.format("user with id %d not found", id));
    }
}
