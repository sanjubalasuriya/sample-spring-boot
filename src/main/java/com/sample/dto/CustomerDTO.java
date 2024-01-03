package com.sample.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDTO {

    private long student_id;
    private String firstName;
    private String lastName;
    private String address;
    private String email;
}
