package com.sample.service;

import com.sample.dto.paginated.ResponseOrderDetailsPaginatedDTO;
import com.sample.dto.request.RequestOrderSaveDTO;

public interface OrderService {
    String saveOrder(RequestOrderSaveDTO requestOrderSaveDTO);

    ResponseOrderDetailsPaginatedDTO getAllIOrderDetails(int page, int size);
}
