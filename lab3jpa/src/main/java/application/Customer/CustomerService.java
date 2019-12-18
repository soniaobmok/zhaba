package application.Customer;

import application.Default.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service("CustomerService")
public class CustomerService implements IService<Customer> {
    @Autowired
    /*private*/ CustomerRepository customerRepo;

    public CustomerService() {}

    @Override
    public Customer Add(Customer customer) throws SQLException {
        customer.setId(GetAll().size() + 1);
        return customerRepo.save(customer);
    }

    @Override
    public void Delete(Customer customer) throws SQLException {
        customerRepo.delete(customer);
    }

    @Override
    public void DeleteById(int id) throws SQLException {
        customerRepo.deleteById(id);//////////////////////////////////////////////////
    }

    @Override   // maybe l8r
    public void Update(Customer customer) throws SQLException {
        Customer c = customerRepo.findByEmail(customer.getEmail());
        c.setName(customer.getName());
        customerRepo.save(c);
    }

    @Override
    public List<Customer> GetAll() throws SQLException {
        List<Customer> list = new ArrayList<>();
        customerRepo.findAll().forEach(list::add);
        return list;
    }

    @Override
    public Customer GetById(int id) throws SQLException {
        return customerRepo.findById(id).orElse(null);
    }
}
