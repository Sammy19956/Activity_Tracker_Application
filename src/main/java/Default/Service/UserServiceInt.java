package Default.Service;

import Default.DTO.LoginDTO;
import Default.DTO.RegistrationDTO;
import Default.Model.User;

public interface UserServiceInt {
    User registerUser(RegistrationDTO registrationDTO);

    boolean Login(LoginDTO loginDTO);

    User findUser(long userId);
}
