package com.sample.service;

import com.sample.dto.request.RequestOrderSaveDTO;

public interface OrderService {
    String saveOrder(RequestOrderSaveDTO requestOrderSaveDTO);
}
