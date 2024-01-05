package com.sample.controller;

import com.sample.dto.request.RequestItemDTO;
import com.sample.dto.request.RequestOrderSaveDTO;
import com.sample.service.OrderService;
import com.sample.util.StandardResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/order")
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/save")
    public ResponseEntity<StandardResponse> saveOrder(@RequestBody RequestOrderSaveDTO requestOrderSaveDTO) {

        String message = orderService.saveOrder(requestOrderSaveDTO);

        return new ResponseEntity<StandardResponse>(new StandardResponse(201, "success", message)
                , HttpStatus.CREATED);
    }
}
