package application.Customer;

import application.Ticket.Ticket;
import org.hibernate.dialect.CUBRIDDialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/{id}") // works
    public String GetById(@PathVariable int id) throws SQLException {
        Customer c = customerService.GetById(id);
        System.out.println("FIND customer BY ID (" + c.getId() + "): " + c.getName() + " " + c.getEmail() + " " + c.getDateOfBirth());
        return c.toString();
    }

    @GetMapping("/")  // works
    public List<String> getAll() throws SQLException {
        List<Customer> customers = customerService.GetAll();
        List<String> list = new ArrayList<>();
        for (Customer item : customers) {
            list.add(item.toString());
        }
        return list;
    }

    @GetMapping("/tickets/{id}")  // daaaaa suka it works
    public List<String> GetAllTickets(@PathVariable int id) throws SQLException {
        List<Ticket> tickets = customerService.customerRepo.GetAllTickets(id);
        List<String> list = new ArrayList<>();
        for (Ticket item : tickets) {
            list.add(item.toString());
        }
        return list;
    }

    @PostMapping("/{name}/{email}/{date}")   // works
    public Customer Add(@PathVariable String name, @PathVariable String email, @PathVariable String date) throws SQLException {
        return customerService.Add(new Customer(name, email, date))/*.toString()*/;
    }

    @PostMapping("/update/{name}/{email}/{date}") // works
    public void UpdateCustomer(@PathVariable String name, @PathVariable String email, @PathVariable String date) throws SQLException {
        customerService.Update(new Customer(name, email, date));
    }

    @PostMapping("/delete/{id}")  // works
    public void DeleteCustomer(@PathVariable int id) throws SQLException {
        customerService.DeleteById(id);
        System.out.println("A customer with id (" + id + ") was successfully deleted!");
    }
}
