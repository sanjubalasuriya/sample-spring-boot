package com.sample.service.serviceImpl;

import com.sample.dto.CustomerDTO;
import com.sample.dto.request.RequestCustomerDTO;
import com.sample.dto.response.ResponseCustomerDTO;
import com.sample.entity.Customer;
import com.sample.repository.CustomerRepository;
import com.sample.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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

//        return null;


//
//    //Save Student
//
//    @Override
//    public String saveStudent(StudentDto studentDto) {
//
//        Student student = modelMapper.map(studentDto, Student.class);
//        if (!studentRepo.existsById(student.getStudentId())) {
//            studentRepo.save(student);
//            return student.getStudentId() + " saved successfully";
//        } else {
//            throw new DuplicateKeyException("Already Added");
//        }
//    }
//
//    //Update Student
//
//    @Override
//    public String updateStudent(StudentDto studentDto) {
//        Student student = modelMapper.map(studentDto, Student.class);
//        if (studentRepo.existsById(studentDto.getStudentId())){
//            studentRepo.save(student);
//            return student.getStudentId() + " saved successfully";
//        }else {
//            throw new NotFoundException("Student does not exist");
//        }
//    }
//
//    //Search by Id
//
//    @Override
//    public StudentDto findByStudentId(String studentId) {
//        if(studentRepo.existsById(studentId)){
//            Student student = studentRepo.getReferenceById(studentId);
//            StudentDto studentDto = modelMapper.map(student,StudentDto.class);
//            return studentDto;
//        }else {
//            throw new NotFoundException("No data");
//        }
//    }
//
//    //Delete
//
//    @Override
//    public String deleteStudent(String id) {
//        if(studentRepo.existsById(id)){
//            studentRepo.deleteById(id);
//            return "Deleted successfully "+id;
//        }else {
//            throw new NotFoundException("Does not found Student");
//        }
//    }
//
//    //Get All Pagination
//
//    @Override
//    public PaginatedResponseStudentDto findStudentsByPagination(int page, int size) {
//        Page<Student> students=studentRepo.findAll(PageRequest.of(page, size));
//
//        if(students.getSize()<1){
//            throw new NotFoundException("No Data");
//        }
//        PaginatedResponseStudentDto paginatedResponseStudentDto = new PaginatedResponseStudentDto(
//                listMapper.studentListToPage(students),
//                (int) studentRepo.count()
//        );
//        return paginatedResponseStudentDto;
//
//    }
}
