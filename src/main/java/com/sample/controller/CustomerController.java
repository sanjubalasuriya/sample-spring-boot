package com.sample.controller;

import com.sample.dto.request.RequestCustomerDTO;
import com.sample.dto.response.ResponseCustomerDTO;
import com.sample.service.CustomerService;
import com.sample.util.StandardResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<StandardResponse> saveCustomer(@RequestBody RequestCustomerDTO requestCustomerDTO){

        String message = customerService.saveCustomer(requestCustomerDTO);

        return new ResponseEntity<StandardResponse>(new StandardResponse(201,"success",message)
                , HttpStatus.CREATED);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<StandardResponse> getCustomerById(@PathVariable Long customerId){

        ResponseCustomerDTO message = customerService.findByCustomerId(customerId);
        return new ResponseEntity<StandardResponse>(new StandardResponse(200,"success",message)
                , HttpStatus.OK);
    }
//
//    @PutMapping("/update")
//    public ResponseEntity<CustomerDTO> updateCustomer( @RequestBody CustomerDTO customerDTO){
//        CustomerDTO customerDTO1 = customerService.updateCustomer(customerDTO);
//        return new ResponseEntity<CustomerDTO>(customerDTO1, HttpStatus.CREATED);
//    }
//
//    @GetMapping("/all")
//    public List<CustomerDTO> getAllCustomers(){
//        List<CustomerDTO> allCustomers = customerService.getAllCustomers();
//        return allCustomers;
//    }
}
