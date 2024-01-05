package com.sample.controller;


import com.sample.dto.paginated.ResponseOrderDetailsPaginatedDTO;
import com.sample.dto.request.RequestOrderSaveDTO;
import com.sample.service.OrderService;
import com.sample.util.StandardResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(
            path = "/paginate-all",
            params = {"page", "size"}
    )
    public ResponseEntity<StandardResponse> getAllIOrderDetails(
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") int size
    ) {
        ResponseOrderDetailsPaginatedDTO responseOrderDetailsPaginatedDTO = orderService.getAllIOrderDetails(page, size);
//        List<CustomerDTO> allCustomers = customerService.getAllCustomersPaginate(page, size);
        return new ResponseEntity<StandardResponse>(new StandardResponse(200, "success", responseOrderDetailsPaginatedDTO)
                , HttpStatus.OK);
    }
}
