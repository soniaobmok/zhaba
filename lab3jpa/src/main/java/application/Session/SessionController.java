package application.Session;

import application.Ticket.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/sessions")
public class SessionController {
    @Autowired
    private SessionService sessionService;

    @GetMapping("/{id}")  // works
    public String GetById(@PathVariable int id) throws SQLException {
        Session s = sessionService.GetById(id);
        System.out.println("FIND session BY ID (" + s.getId() + "): " + s.getDate() + " " + s.getMovie());
        return s.toString();
    }

    @GetMapping("/") // works
    public List<String> getAll() throws SQLException {
        List<Session> sessions = sessionService.GetAll();
        List<String> list = new ArrayList<>();
        for (Session item : sessions) {
            list.add(item.toString());
        }
        return list;
    }

    @GetMapping("/date/{date}")
    public List<String> getByDate(@PathVariable(name="date") String date) throws SQLException {
        List<Session> sessions = sessionService.sessionRepo.findByDate(date);
        List<String> list = new ArrayList<>();
        for (Session item : sessions) {
            list.add(item.toString());
        }
        return list;
    }

    @GetMapping("/title/{title}")
    public List<String> getByTitle(@PathVariable(name="title") String title) throws SQLException {
        List<Session> sessions = sessionService.sessionRepo.findByMovie(title);
        List<String> list = new ArrayList<>();
        for (Session item : sessions) {
            list.add(item.toString());
        }
        return list;
    }

    @PostMapping("/{date}/{title}") // works
    public Session Add(@PathVariable String title, @PathVariable String date) throws SQLException {
        return sessionService.Add(new Session(title, date));
    }
}
