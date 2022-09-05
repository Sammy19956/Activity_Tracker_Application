package Default.DTO;

import Default.Enums.UserRole;
import Default.Model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private UserRole userRole;

}
