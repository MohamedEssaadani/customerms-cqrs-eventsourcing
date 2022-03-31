package org.essaadani.customermscqrseventsourcing.commonapi.commands;

import lombok.Data;
import lombok.Getter;

import java.util.Date;

public class CreateCustomerCommand extends BaseCommand<String>{
    /*
    * what we need to create customer
    * */
    @Getter private String firstName;
    @Getter private String lastName;
    @Getter private Date birthDate;
    @Getter private String cin;
    @Getter private Date timestamp;

    public CreateCustomerCommand(String id, String firstName, String lastName, Date birthDate, String cin, Date timestamp) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.cin = cin;
        this.timestamp = timestamp;
    }
}
