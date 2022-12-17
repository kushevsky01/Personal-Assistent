package web.note.perository;
import org.springframework.data.jpa.repository.JpaRepository;
import web.note.model.ToDoList;
import web.note.model.User;

public interface ToDoListRepository extends JpaRepository<ToDoList, Long> {

    ToDoList findByIdAndNameAndUser(Long id,String name,  User user);

}
