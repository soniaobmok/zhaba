package application.Session;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionRepository extends CrudRepository<Session, Integer> {

    List<Session> findByDate(String date);

    List<Session> findByMovie(String movie);
}
