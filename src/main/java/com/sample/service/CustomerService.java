package com.sample.service;

import com.sample.dto.CustomerDTO;
import com.sample.dto.request.RequestCustomerDTO;
import com.sample.dto.response.ResponseCustomerDTO;

public interface CustomerService {
    String saveCustomer(RequestCustomerDTO requestCustomerDTO);


    ResponseCustomerDTO findByCustomerId(Long customerId);

    String updateCustomer(CustomerDTO customerDTO);
}
