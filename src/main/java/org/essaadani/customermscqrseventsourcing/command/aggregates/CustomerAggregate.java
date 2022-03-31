package org.essaadani.customermscqrseventsourcing.command.aggregates;


import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.essaadani.customermscqrseventsourcing.commonapi.commands.CreateCustomerCommand;
import org.essaadani.customermscqrseventsourcing.commonapi.commands.UpdateCustomerCommand;
import org.essaadani.customermscqrseventsourcing.commonapi.events.CustomerCreatedEvent;
import org.essaadani.customermscqrseventsourcing.commonapi.events.CustomerUpdatedEvent;

import java.util.Date;

@Aggregate
public class CustomerAggregate {
    /*
    * App current state
    * */
    @AggregateIdentifier
    private String customerId;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String cin;
    private Date timestamp;

    public CustomerAggregate() {
    }

    /*
    * Commands Handlers
    * */
    @CommandHandler
    public CustomerAggregate(CreateCustomerCommand command){
        /*
        * Business Logic
        * */
        if(command.getId() == null || command.getFirstName() == null || command.getLastName() == null
                || command.getBirthDate() == null || command.getCin() == null)
            throw new RuntimeException("All parameters are required!");

        /*
        * Emit CustomerCreatedEvent to save data to event store
        * */
        AggregateLifecycle.apply(new CustomerCreatedEvent(
                command.getId(),
                command.getFirstName(),
                command.getLastName(),
                command.getBirthDate(),
                command.getCin(),
                command.getTimestamp()
        ));
    }

    @CommandHandler
    public void handle(UpdateCustomerCommand command){
        /*
         * Business Logic
         * */
        if(command.getId() == null || command.getFirstName() == null || command.getLastName() == null )
            throw new RuntimeException("All parameters are required!");

        /*
         * Emit CustomerCreatedEvent to save data to event store
         * */
        AggregateLifecycle.apply(new CustomerUpdatedEvent(
                command.getId(),
                command.getFirstName(),
                command.getLastName(),
                command.getTimestamp()
        ));
    }

    /*
    * Event sourcing handlers
    * */
    @EventSourcingHandler
    public void on(CustomerCreatedEvent event){
        this.customerId = event.getId();
        this.firstName = event.getFirstName();
        this.lastName = event.getLastName();
        this.birthDate = event.getBirthDate();
        this.cin = event.getCin();
        this.timestamp = event.getTimestamp();
    }

    @EventSourcingHandler
    public void on(CustomerUpdatedEvent event){
        this.firstName = event.getFirstName();
        this.lastName = event.getLastName();
        this.timestamp = event.getTimestamp();
    }
}
