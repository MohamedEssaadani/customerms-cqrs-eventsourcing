package org.essaadani.customermscqrseventsourcing.commonapi.queries;

import lombok.Data;

@Data
public class GetCustomerByIdQuery {
    private String customerId;
}
