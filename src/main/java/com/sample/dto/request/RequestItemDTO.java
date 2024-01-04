package com.sample.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestItemDTO {

    private String name;
    private int qty;
    private double unitPrice;
}
