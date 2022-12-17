package web.note.perository;

import org.springframework.data.jpa.repository.JpaRepository;
import web.note.model.Affairs;

public interface AffairsRepository extends JpaRepository<Affairs, Long> {

}
