package com.sample.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseCustomerDTO {

    private long student_id;
    private String firstName;
    private String lastName;
    private String address;
    private String email;
}
