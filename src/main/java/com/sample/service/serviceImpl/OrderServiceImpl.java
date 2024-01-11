package com.sample.service.serviceImpl;


import com.sample.dto.paginated.ResponseItemPaginatedDTO;
import com.sample.dto.paginated.ResponseOrderDetailsPaginatedDTO;
import com.sample.dto.queryInterface.OrderDetailsInterface;
import com.sample.dto.request.RequestOrderDetailsSave;
import com.sample.dto.request.RequestOrderSaveDTO;
import com.sample.dto.response.ResponseOrderDetailsDTO;
import com.sample.entity.OrderDetails;
import com.sample.entity.Orders;
import com.sample.repository.CustomerRepository;
import com.sample.repository.ItemRepository;
import com.sample.repository.OrderDetailsRepository;
import com.sample.repository.OrderRepository;
import com.sample.service.OrderService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
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

    @Override
    public ResponseOrderDetailsPaginatedDTO getAllIOrderDetails(int page, int size) {
        List<OrderDetailsInterface> orderDetailsInterfaces = orderRepository.getOrderDetails(PageRequest.of(page, size));

        List<ResponseOrderDetailsDTO> responseOrderDetailsDTOS = new ArrayList<>();

        for(OrderDetailsInterface orderDetailsInterface: orderDetailsInterfaces){
            ResponseOrderDetailsDTO responseOrderDetailsDTO = new ResponseOrderDetailsDTO(
                    orderDetailsInterface.getFirstName(),
                    orderDetailsInterface.getLastName(),
                    orderDetailsInterface.getAddress(),
                    orderDetailsInterface.getEmail(),
                    orderDetailsInterface.getTotal(),
                    orderDetailsInterface.getCreated_At()
            );
            responseOrderDetailsDTOS.add(responseOrderDetailsDTO);
        }



        return new ResponseOrderDetailsPaginatedDTO(
                responseOrderDetailsDTOS,
                orderRepository.orderDetailsCount()
        );
    }
}
