package com.sample.controller;

import com.sample.dto.CustomerDTO;
import com.sample.dto.paginated.ResponseCustomerPaginatedDTO;
import com.sample.dto.request.RequestCustomerDTO;
import com.sample.dto.response.ResponseCustomerDTO;
import com.sample.service.CustomerService;
import com.sample.util.StandardResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customer")
@AllArgsConstructor
public class CustomerController {


    private final CustomerService customerService;

    @PostMapping("/save")
    public ResponseEntity<StandardResponse> saveCustomer(@RequestBody RequestCustomerDTO requestCustomerDTO) {

        String message = customerService.saveCustomer(requestCustomerDTO);

        return new ResponseEntity<StandardResponse>(new StandardResponse(201, "success", message)
                , HttpStatus.CREATED);
    }

    @GetMapping(
            path = "/get-by-id",
            params = "customerId"
    )
    public ResponseEntity<StandardResponse> getCustomerById(long customerId) {

        ResponseCustomerDTO message = customerService.findByCustomerId(customerId);
        return new ResponseEntity<StandardResponse>(new StandardResponse(200, "success", message)
                , HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<StandardResponse> updateCustomer(@RequestBody CustomerDTO customerDTO) {
        String message = customerService.updateCustomer(customerDTO);
        return new ResponseEntity<StandardResponse>(new StandardResponse(200, "success", message)
                , HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<StandardResponse> getAllCustomers() {
        List<CustomerDTO> allCustomers = customerService.getAllCustomers();
        return new ResponseEntity<StandardResponse>(new StandardResponse(200, "success", allCustomers)
                , HttpStatus.OK);
    }

    @GetMapping(
            path = "/paginate-all",
            params = {"page", "size"}
    )
    public ResponseEntity<StandardResponse> getAllCustomersPaginate(
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") int size
    ) {
        ResponseCustomerPaginatedDTO responseCustomerPaginatedDTO = customerService.getAllCustomersPaginate(page, size);
//        List<CustomerDTO> allCustomers = customerService.getAllCustomersPaginate(page, size);
        return new ResponseEntity<StandardResponse>(new StandardResponse(200, "success", responseCustomerPaginatedDTO)
                , HttpStatus.OK);
    }
}
