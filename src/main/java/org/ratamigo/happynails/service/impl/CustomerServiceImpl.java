package org.ratamigo.happynails.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.ratamigo.happynails.dto.CustomerDto;
import org.ratamigo.happynails.dto.CustomerGetAllResponse;
import org.ratamigo.happynails.exceptions.CustomerNotFoundException;
import org.ratamigo.happynails.model.Customer;
import org.ratamigo.happynails.repository.CustomerRepository;
import org.ratamigo.happynails.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepo;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepo = customerRepository;
    }

    public CustomerDto mapToDto(Customer customer) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setName(customer.getName());
        customerDto.setEmail(customer.getEmail());
        customerDto.setId(customer.getId());
        customerDto.setPhone(customer.getPhone());
        customerDto.setPreviousServiceId(customer.getPreviousServiceId());

        return customerDto;
    }

    public Customer mapToEntity(CustomerDto dto) {
        Customer customer = new Customer();
        customer.setName(dto.getName());
        customer.setEmail(dto.getEmail());
        customer.setPhone(dto.getPhone());
        customer.setPreviousServiceId(dto.getPreviousServiceId());

        return customer;
    }

	@Override
	public CustomerDto createCustomer(CustomerDto customerDto) {
		Customer customer = mapToEntity(customerDto);
        Customer newCustomer = customerRepo.save(customer);
        CustomerDto customerResponse = mapToDto(newCustomer);
        return customerResponse;
	}

	@Override
	public CustomerGetAllResponse getAllCustomers(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
		Page<Customer> customers = customerRepo.findAll(pageable);
        List<Customer> listOfCustomers = customers.getContent();
        List<CustomerDto> content = listOfCustomers.stream().map(
                    c -> mapToDto(c)).collect(Collectors.toList());
        CustomerGetAllResponse customerResponse = new CustomerGetAllResponse();
        customerResponse.setContent(content);
        customerResponse.setPageNo(customers.getNumber());
        customerResponse.setPageSize(customers.getSize());
        customerResponse.setTotalElements(customers.getTotalElements());
        customerResponse.setTotalPages(customers.getTotalPages());
        customerResponse.setLast(customers.isLast());

        return customerResponse;
	}

	@Override
	public CustomerDto getCustomerById(int id) {
		Customer customer = customerRepo.findById(id).orElseThrow(()
                -> new CustomerNotFoundException("Customer could not be found"));
        return mapToDto(customer);
	}

	@Override
	public CustomerDto updateCustomer(CustomerDto customerDto, int id) {
		Customer customer = customerRepo.findById(id).orElseThrow(()
                -> new CustomerNotFoundException("Customer could not be updated"));
        if (customerDto.getEmail() != null) {
            customer.setEmail(customerDto.getEmail());
        }
        if(customerDto.getName() != null) {
            customer.setName(customerDto.getName());
        }
        if(customerDto.getPhone() != null) {
            customer.setPhone(customerDto.getPhone());
        }
        customer.setPreviousServiceId(customerDto.getPreviousServiceId());
        Customer updatedCustomer = customerRepo.save(customer);

        return mapToDto(updatedCustomer);
	}

	@Override
	public void deleteCustomer(int id) {
		Customer customer = customerRepo.findById(id).orElseThrow(()
                -> new CustomerNotFoundException("Customer could not be deleted"));
        customerRepo.delete(customer);
	}
}
