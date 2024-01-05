package com.sample.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestOrderDetailsSave {


    private String itemName;
    private double qty;
    private Double amount;
    private long item;
    private int orders;

    public RequestOrderDetailsSave(String itemName, double qty, Double amount, long item) {
        this.itemName = itemName;
        this.qty = qty;
        this.amount = amount;
        this.item = item;
    }
}
