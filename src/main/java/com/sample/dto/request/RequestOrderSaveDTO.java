package com.sample.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestOrderSaveDTO {

    private Double total;
    private long customer;
    private List<RequestOrderDetailsSave> orderDetails;
}
