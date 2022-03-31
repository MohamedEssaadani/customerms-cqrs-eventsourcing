package org.essaadani.customermscqrseventsourcing.command.controllers;

import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.essaadani.customermscqrseventsourcing.commonapi.commands.CreateCustomerCommand;
import org.essaadani.customermscqrseventsourcing.commonapi.commands.UpdateCustomerCommand;
import org.essaadani.customermscqrseventsourcing.commonapi.dtos.CreateCustomerRequestDTO;
import org.essaadani.customermscqrseventsourcing.commonapi.dtos.UpdateCustomerRequestDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@RestController
@RequestMapping("/commands/customers")
@AllArgsConstructor
public class CustomerCommandController {
    private CommandGateway commandGateway;
    private EventStore eventStore;

    // create customer
    @PostMapping
    public CompletableFuture<String> createCustomer(@RequestBody CreateCustomerRequestDTO request){
        CompletableFuture<String> commandResponse = commandGateway.send(new CreateCustomerCommand(
                UUID.randomUUID().toString(),
                request.getFirstName(),
                request.getLastName(),
                request.getBirthDate(),
                request.getCin(),
                new Date()
        ));

        return commandResponse;
    }
    // update customer
    @PutMapping
    public CompletableFuture<String> updateCustomer(@RequestBody UpdateCustomerRequestDTO request){
        CompletableFuture<String> commandResponse = commandGateway.send(new UpdateCustomerCommand(
                request.getCustomerId(),
                request.getFirstName(),
                request.getLastName(),
                new Date()
        ));

        return commandResponse;
    }

    @GetMapping("/eventStore/{customerId}")
    public Stream eventStore(@PathVariable String customerId){
        return eventStore.readEvents(customerId).asStream();
    }

    // exception handler
    @ExceptionHandler
    public ResponseEntity<String> exceptionHandler(Exception exception){
        ResponseEntity<String> responseEntity = new ResponseEntity<>(
                exception.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR
        );

        return responseEntity;
    }

}
