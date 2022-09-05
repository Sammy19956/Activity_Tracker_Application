package Default.Repositories;

import Default.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long>{

    @Query(value= "SELECT * FROM users WHERE email=?1 AND password = ?2", nativeQuery = true)
    User findByEmailAndPassword(String email, String password);

}
