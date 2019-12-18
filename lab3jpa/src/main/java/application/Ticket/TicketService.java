package application.Ticket;

import application.Customer.Customer;
import application.Customer.CustomerRepository;
import application.Default.IService;
import application.Seat.Seat;
import application.Seat.SeatRepository;
import application.Session.Session;
import application.Session.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service("TicketService")
public class TicketService implements IService<Ticket> {
    @Autowired
    private TicketRepository ticketRepo;
    @Autowired
    public CustomerRepository customerRepo;
    @Autowired
    public SessionRepository sessionRepo;
    @Autowired
    public SeatRepository seatRepo;

    public TicketService() {};

    @Override
    public Ticket Add(Ticket ticket) throws SQLException {
        ticket.setId(GetAll().size() + 1);
        System.out.println("Ticket added. Message In tickServ Add Method.");
        return ticketRepo.save(ticket);
    }

    @Override
    public void Delete(Ticket ticket) throws SQLException {
        ticketRepo.delete(ticket);
    }

    @Override
    public void DeleteById(int id) throws SQLException {
        ticketRepo.deleteById(id);
    }

    @Override
    public void Update(Ticket ticket) throws SQLException {       ///////////////////////
        Ticket t = ticketRepo.findById(ticket.getId()).orElse(null);
        t.setSeat(ticket.getSeat());
    }

    @Override
    public List<Ticket> GetAll() throws SQLException {
        List<Ticket> list = new ArrayList<>();
        ticketRepo.findAll().forEach(list::add);
        return list;
    }

    @Override
    public Ticket GetById(int id) throws SQLException {
        return ticketRepo.findById(id).orElse(null);
    }

    public Ticket BookTicket(Customer customer, Session session, Seat seat) throws SQLException {
        Ticket ticket = new Ticket(customer, session, seat);
        System.out.println("New ticket: " + ticket.toString());
        return ticket;
    }
}
