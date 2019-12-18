package application.Ticket;

import application.Customer.Customer;
import application.Seat.Seat;
import application.Session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @GetMapping("/{id}") // works
    public String GetById(@PathVariable int id) throws SQLException {
        return ticketService.GetById(id).toString();
    }

    @GetMapping("/") // works
    public List<String> getAll() throws SQLException {
        List<Ticket> tickets = ticketService.GetAll();
        List<String> list = new ArrayList<>();
        for (Ticket item : tickets) {
            list.add(item.toString());
        }
        return list;
    }

    @PostMapping("/{customerId}/{sessionId}/{seatId}")   // works
    public String BookTicket(@PathVariable int customerId, @PathVariable int sessionId, @PathVariable int seatId) throws SQLException {
        Customer customer = ticketService.customerRepo.findById(customerId).orElse(null);
        Session session = ticketService.sessionRepo.findById(sessionId).orElse(null);
        Seat seat = ticketService.seatRepo.findById(seatId).orElse(null);//////////////// check if seat is free

        if (seat.getFree()) {
            seat.setFree(false);
            ticketService.seatRepo.save(seat);
            return ticketService.Add(ticketService.BookTicket(customer, session, seat)).toString();
        }
        return new String("Can't book a ticket! A seat is already occupied!");
    }

    @PostMapping("/delete/{id}")  // works
    public void DeleteTicket(@PathVariable int id) throws SQLException {
        ticketService.DeleteById(id);
        System.out.println("A ticket with id (" + id + ") was successfully deleted!");
    }
}
