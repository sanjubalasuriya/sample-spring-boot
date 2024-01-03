package com.sample.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestCustomerDTO {

    private String firstName;
    private String lastName;
    private String address;
    private String email;
}
