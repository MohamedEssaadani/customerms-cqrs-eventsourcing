package org.essaadani.customermscqrseventsourcing.commonapi.dtos;

import lombok.Data;

@Data
public class UpdateCustomerRequestDTO {
    private String customerId;
    private String firstName;
    private String lastName;
}
