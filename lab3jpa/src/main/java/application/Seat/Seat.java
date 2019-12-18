package application.Seat;

import application.Ticket.Ticket;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Seats")
public class Seat {
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "seatId")
    private int id;
    @Column(name = "seatNumber")
    private int seatNumber;
    @Column(name = "rowNumber")
    private int rowNumber;
    @Column(name = "isFree")
    private boolean isFree;

    @OneToMany(mappedBy = "seat", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Ticket> tickets;

    public boolean getFree() {
        return this.isFree;
    }

    @Override
    public String toString() {
        return "Seat{" + "id=" + id +
                ", seatNumber=" + seatNumber +
                ", rowNumber=" + rowNumber +
                ", isFree=" + isFree +
                '}';
    }
}
