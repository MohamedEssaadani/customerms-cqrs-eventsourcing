package org.essaadani.customermscqrseventsourcing.query.controllers;

import lombok.AllArgsConstructor;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.essaadani.customermscqrseventsourcing.commonapi.queries.GetAllCustomersQuery;
import org.essaadani.customermscqrseventsourcing.query.entities.Customer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/queries/customers")
public class CustomersQueryController {
    private QueryGateway queryGateway;

    @GetMapping
    public List<Customer> getAllCustomers(){
        // join() => return the result
        List<Customer> customers = queryGateway.query(new GetAllCustomersQuery(), ResponseTypes.multipleInstancesOf(Customer.class)).join();

        return customers;
    }

    @GetMapping("/{customerId}")
    public Customer getCustomerById(@PathVariable String customerId){
        Customer customer = queryGateway.query(new GetAllCustomersQuery(), ResponseTypes.instanceOf(Customer.class)).join();

        return customer;
    }
}
