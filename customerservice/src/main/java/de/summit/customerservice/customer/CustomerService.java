package de.summit.customerservice.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void createCustomer(String name, String address, String lineOfBusiness) {
        Customer customer = new Customer();
        customer.setAddress(address);
        customer.setName(name);
        customer.setLineOfBusiness(lineOfBusiness);
        customerRepository.save(customer);
    }

    public Iterable<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

}
