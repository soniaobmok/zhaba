package application.Session;

import application.Default.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service("SessionService")
public class SessionService implements IService<Session> {
    @Autowired
    public SessionRepository sessionRepo;

    public SessionService() {}

    @Override
    public Session Add(Session session) throws SQLException {
        session.setId(GetAll().size() + 1);
        return sessionRepo.save(session);
    }

    @Override
    public void Delete(Session session) throws SQLException {
        sessionRepo.delete(session);
    }

    @Override
    public void DeleteById(int id) throws SQLException {
        sessionRepo.deleteById(id);
    }

    @Override
    public void Update(Session session) throws SQLException {
        Session s = sessionRepo.findById(session.getId()).orElse(null);
        s.setMovie(session.getMovie());
        s.setDate(session.getDate());
    }

    @Override
    public List<Session> GetAll() throws SQLException {
        List<Session> list = new ArrayList<>();
        sessionRepo.findAll().forEach(list::add);
        return list;
    }

    @Override
    public Session GetById(int id) throws SQLException {
        return sessionRepo.findById(id).orElse(null);
    }
}
