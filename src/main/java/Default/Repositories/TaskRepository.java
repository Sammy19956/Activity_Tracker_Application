package Default.Repositories;

import Default.DTO.TaskDTO;
import java.util.*;

import Default.Enums.Status;
import Default.Model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TaskRepository extends JpaRepository<Task, Long>{

    @Query(value= "SELECT * FROM task WHERE status=?1", nativeQuery = true)
    List<Task> findTaskByStatus(Status status);
}
