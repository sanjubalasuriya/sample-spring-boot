package com.sample.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "order_details")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDetails {

    @Id
    @Column(name = "order_details_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderDetailsId;

    private String itemName;

    private double qty;

    private Double amount;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Orders orders;

    public OrderDetails(String itemName, double qty, Double amount, Item item, Orders orders) {
        this.itemName = itemName;
        this.qty = qty;
        this.amount = amount;
        this.item = item;
        this.orders = orders;
    }


}
