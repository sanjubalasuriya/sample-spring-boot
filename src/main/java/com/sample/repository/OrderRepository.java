package com.sample.repository;

import com.sample.dto.queryInterface.OrderDetailsInterface;
import com.sample.entity.Orders;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Orders, Integer> {

    @Query(value = "select c.first_name as FirstName, c.last_name as LastName, c.address as Address, c.email as Email, o.total as Total, o.created_at as Created_At from customer c,orders o where c.customer_id = o.customer_id",nativeQuery = true)
    List<OrderDetailsInterface> getOrderDetails(Pageable pageable);

    @Query(value = "select count(*) from customer c,orders o where c.customer_id = o.customer_id",nativeQuery = true)
    long orderDetailsCount();
}
