package application.Customer;

import application.Ticket.Ticket;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Customers")
public class Customer {
    @Id
    @Column(name = "customerId")
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "customerName")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "dateOfBirth")
    private String dateOfBirth;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Ticket> tickets;

    public Customer(String name, String email, String dateOfBirth) {
        this.name = name;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "Customer{" + "id=" + id +
                ", name=" + name +
                ", email=" + email +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }
}
