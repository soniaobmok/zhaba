package application.Customer;

import application.Ticket.Ticket;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {
    @Query("select t from Ticket t where t.customer.id = ?1")
    List<Ticket> GetAllTickets(int id);

    Customer findByEmail(String email);
}
