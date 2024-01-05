package com.sample.service.serviceImpl;

import com.sample.dto.CustomerDTO;
import com.sample.dto.request.RequestOrderDetailsSave;
import com.sample.dto.request.RequestOrderSaveDTO;
import com.sample.entity.Customer;
import com.sample.entity.OrderDetails;
import com.sample.entity.Orders;
import com.sample.repository.CustomerRepository;
import com.sample.repository.ItemRepository;
import com.sample.repository.OrderDetailsRepository;
import com.sample.repository.OrderRepository;
import com.sample.service.OrderService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final CustomerRepository customerRepository;
    private final OrderDetailsRepository orderDetailsRepository;
    private final ItemRepository itemRepository;

    @Override
    @Transactional
    public String saveOrder(RequestOrderSaveDTO requestOrderSaveDTO) {
        Orders orders = new Orders(
                requestOrderSaveDTO.getTotal(),
                customerRepository.getReferenceById(requestOrderSaveDTO.getCustomer())
        );

        orderRepository.save(orders);


        if (orderRepository.existsById(orders.getOrderId())) {
            List<RequestOrderDetailsSave> orderDetailsSaveList = requestOrderSaveDTO.getOrderDetails();
            List<OrderDetails> orderDetailsList = new ArrayList<>();


            for (RequestOrderDetailsSave orderDetailsSave : orderDetailsSaveList) {
                OrderDetails orderDetails = new OrderDetails(

                        orderDetailsSave.getItemName(),
                        orderDetailsSave.getQty(),
                        orderDetailsSave.getAmount(),
                        itemRepository.getReferenceById(orderDetailsSave.getItem()),
                        orders
                );
                orderDetailsList.add(orderDetails);
            }
            if (!orderDetailsList.isEmpty()){
                orderDetailsRepository.saveAll(orderDetailsList);
            }


            return "saved";

        }
        return null;
    }
}
