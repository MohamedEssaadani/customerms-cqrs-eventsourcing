package org.essaadani.customermscqrseventsourcing.query.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.essaadani.customermscqrseventsourcing.commonapi.events.CustomerCreatedEvent;
import org.essaadani.customermscqrseventsourcing.commonapi.events.CustomerUpdatedEvent;
import org.essaadani.customermscqrseventsourcing.commonapi.queries.GetAllCustomersQuery;
import org.essaadani.customermscqrseventsourcing.commonapi.queries.GetCustomerByIdQuery;
import org.essaadani.customermscqrseventsourcing.query.entities.Customer;
import org.essaadani.customermscqrseventsourcing.query.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
@Slf4j
@Transactional
public class CustomerServiceHandler {
    private CustomerRepository customerRepository;
    /*
     * Listen to events
     * */
    @EventHandler
    public void on(CustomerCreatedEvent event){
        log.info("*******************************************");
        log.info("CustomerCreatedEvent received!");
        log.info("*******************************************");
        Customer customer = new Customer();
        customer.setCustomerId(event.getId());
        customer.setFirstName(event.getFirstName());
        customer.setLastName(event.getLastName());
        customer.setCin(event.getCin());
        customer.setBirthDate(event.getBirthDate());
        customer.setCreatedAt(event.getTimestamp());

        customerRepository.save(customer);
    }

    @EventHandler
    public void on (CustomerUpdatedEvent event){
        log.info("*******************************************");
        log.info("CustomerUpdatedEvent received!");
        log.info("*******************************************");
        Customer customer = customerRepository.findById(event.getId()).get();
        customer.setFirstName(event.getFirstName());
        customer.setLastName(event.getLastName());
        customer.setUpdatedAt(event.getTimestamp());
    }

    /*
     * Queries Handlers
     * */
    // get all customers
    @QueryHandler
    public List<Customer> getAllCustomers(GetAllCustomersQuery query){
        return customerRepository.findAll();
    }
    // get customer by id
    @QueryHandler
    public Customer getCustomerById(GetCustomerByIdQuery query){
        return customerRepository.findById(query.getCustomerId()).get();
    }
}
