package de.summit.customerservice;

import de.summit.customerservice.customer.Customer;
import de.summit.customerservice.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class CustomerRestController {

    private final CustomerService customerService;

    @Autowired
    public CustomerRestController(CustomerService customerService) {
        this.customerService = customerService;
        customerService.createCustomer("Bitmarck", "Kruppstraße 64, 45145 Essen", "Health");
        customerService.createCustomer("Beschaffungsamt", "Brühler Str. 3, 53119 Bonn", "Public");
        customerService.createCustomer("Postbank Systems AG", "Baunscheidtstraße 8, 53113 Bonn", "Banking");
    }


    @GetMapping(path = "/customer")
    public Iterable<Customer> customer() {
        return customerService.getAllCustomers();
    }


}
