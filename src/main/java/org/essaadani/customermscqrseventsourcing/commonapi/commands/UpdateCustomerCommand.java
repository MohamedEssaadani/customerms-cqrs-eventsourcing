package org.essaadani.customermscqrseventsourcing.commonapi.commands;

import lombok.Getter;

import java.util.Date;

public class UpdateCustomerCommand extends BaseCommand<String>{
    @Getter private String firstName;
    @Getter private String lastName;
    @Getter private Date timestamp;

    public UpdateCustomerCommand(String id, String firstName, String lastName, Date timestamp) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.timestamp = timestamp;
    }
}
