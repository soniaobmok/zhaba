package application.Ticket;

import application.Customer.Customer;
import application.Seat.Seat;
import application.Session.Session;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Tickets")
public class Ticket {
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ticketId", nullable = false, updatable = false)
    private int id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerId")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})///////////
    private Customer customer;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sessionId")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})///////////
    private Session session;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seatId")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})///////////
    private Seat seat;

    public Ticket (Customer customer, Session session, Seat seat) {
        this.customer = customer;
        this.session = session;
        this.seat = seat;
    }

    @Override
    public String toString() {
        return "Ticket{" + "id=" + id +
                ", sessionID=" + session.getId() +
                ", seatID=" + seat.getId() +
                ", customerID=" + customer.getId() +
                '}';
    }
}
