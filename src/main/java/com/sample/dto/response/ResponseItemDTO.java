package com.sample.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseItemDTO {

    private long id;
    private String name;
    private int qty;
    private double unitPrice;
}
