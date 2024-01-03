package com.sample.service.serviceImpl;

import com.sample.dto.CustomerDTO;
import com.sample.dto.paginated.ResponseCustomerPaginatedDTO;
import com.sample.dto.request.RequestCustomerDTO;
import com.sample.dto.response.ResponseCustomerDTO;
import com.sample.entity.Customer;
import com.sample.repository.CustomerRepository;
import com.sample.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {


    private final CustomerRepository customerRepository;

    @Override
    public String saveCustomer(RequestCustomerDTO requestCustomerDTO) {
        Customer customer = new Customer(
                requestCustomerDTO.getFirstName(),
                requestCustomerDTO.getLastName(),
                requestCustomerDTO.getAddress(),
                requestCustomerDTO.getEmail()
        );

        customerRepository.save(customer);

        return "Customer Id "+customer.getCustomer_id();
    }

    @Override
    public ResponseCustomerDTO findByCustomerId(Long customerId) {
        if (customerRepository.existsById(customerId)) {
            Customer customer = customerRepository.getReferenceById(customerId);
            return new ResponseCustomerDTO(
                    customer.getCustomer_id(),
                    customer.getFirstName(),
                    customer.getLastName(),
                    customer.getAddress(),
                    customer.getEmail()
            );
        } else {
            throw new RuntimeException("Not Found");
        }
    }

    @Override
    public String updateCustomer(CustomerDTO customerDTO) {
        if(customerRepository.existsById(customerDTO.getStudent_id())){
            Customer customer = customerRepository.getReferenceById(customerDTO.getStudent_id());
            customer.setFirstName(customerDTO.getFirstName());
            customer.setLastName(customerDTO.getLastName());
            customer.setAddress(customerDTO.getAddress());
            customer.setEmail(customerDTO.getEmail());

            customerRepository.save(customer);
            return customer.getFirstName() +" "+ customer.getLastName() + " Updated";
        }else {
            throw new RuntimeException("No data");
        }

    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> getAllCustomers = customerRepository.findAll();

        List<CustomerDTO> customerDTOList = new ArrayList<>();

        for (Customer customer : getAllCustomers){
            CustomerDTO customerDTO = new CustomerDTO(
                    customer.getCustomer_id(),
                    customer.getFirstName(),
                    customer.getLastName(),
                    customer.getAddress(),
                    customer.getEmail()
            );
            customerDTOList.add(customerDTO);
        }
        return customerDTOList;
    }

    @Override
    public ResponseCustomerPaginatedDTO getAllCustomersPaginate(int page, int size) {
        Page<Customer> customers = customerRepository.findAll(PageRequest.of(page, size));

        List<ResponseCustomerDTO> customerDTOList = new ArrayList<>();

        long count = customerRepository.count();

        for (Customer customer : customers){
            ResponseCustomerDTO customerDTO = new ResponseCustomerDTO(
                    customer.getCustomer_id(),
                    customer.getFirstName(),
                    customer.getLastName(),
                    customer.getAddress(),
                    customer.getEmail()
            );
            customerDTOList.add(customerDTO);
        }


        return new ResponseCustomerPaginatedDTO(
                customerDTOList,
                count
        );
    }



}
