package org.essaadani.customermscqrseventsourcing.commonapi.dtos;

import lombok.Data;

import java.util.Date;

@Data
public class CreateCustomerRequestDTO {
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String cin;
}
