package com.sample.dto.queryInterface;

import java.util.Date;

public interface OrderDetailsInterface {
    String getFirstName();
    String getLastName();
    String getAddress();
    String getEmail();
    Double getTotal();
    Date getCreated_At();

}
