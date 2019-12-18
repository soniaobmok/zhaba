package application.Session;

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
@Table(name = "CinemaSessions")
public class Session {
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "sessionId")
    private int id;
    @Column(name = "sessionDate")
    private String date;
    @Column(name = "movieTitle")
    private String movie;

    @OneToMany(mappedBy = "session", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Ticket> tickets;

    public Session(String date, String movie) {
        this.date = date;
        this.movie = movie;
    }

    @Override
    public String toString() {
        return "Session{" + "id=" + id +
                ", date=" + date +
                ", movie=" + movie +
                '}';
    }
}
