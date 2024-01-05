package com.sample.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseOrderDetailsDTO {

    private String firstName;
    private String lastName;
    private String address;
    private String email;

    private Double total;
    private Date created_At;


}
