package org.essaadani.customermscqrseventsourcing.query.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import javax.persistence.Id;

import javax.persistence.Entity;
import java.util.Date;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Customer {
    @Id
    private String customerId;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String cin;
    private Date createdAt;
    private Date updatedAt;
}
