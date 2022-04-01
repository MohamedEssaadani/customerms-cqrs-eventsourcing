package org.essaadani.customermscqrseventsourcing.commonapi.events;

import lombok.Getter;

import java.util.Date;

public class CustomerUpdatedEvent extends BaseEvent<String>{
    /*
    * what to save to event store
    * */
    @Getter private String firstName;
    @Getter private String lastName;
    @Getter private Date timestamp;

    public CustomerUpdatedEvent(String id, String firstName, String lastName, Date timestamp) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.timestamp = timestamp;
    }
}
