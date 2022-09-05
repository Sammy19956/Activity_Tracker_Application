package Default.Service.Impl;

import Default.DTO.LoginDTO;
import Default.DTO.RegistrationDTO;
import Default.Enums.UserRole;
import Default.Model.User;
import Default.Repositories.UserRepository;
import Default.Service.UserServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserServiceInt {

    @Autowired
    UserRepository userRepository;

    @Override
    public User registerUser(RegistrationDTO registrationDTO) {
        User user = new User();
        user.setFirstName(registrationDTO.getFirstName());
        user.setLastName(registrationDTO.getLastName());
        user.setEmail(registrationDTO.getEmail());
        user.setPassword(registrationDTO.getPassword());
        user.setRole(UserRole.CUSTOMER);

        return userRepository.save(user);
    }

    @Override
    public boolean Login(LoginDTO loginDTO) {
        return (userRepository.findByEmailAndPassword(loginDTO.getEmail(),loginDTO.getPassword()))!= null;
    }

    @Override
    public User findUser(long userId) {
        User user= userRepository.findById(userId).orElse(null);
        return null;
    }
}
