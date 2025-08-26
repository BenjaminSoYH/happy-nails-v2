package org.ratamigo.happynails.service;

import java.util.List;

import org.ratamigo.happynails.dto.CustomerDto;


public interface CustomerService {
    CustomerDto createCustomer(CustomerDto customerDto);
    List<CustomerDto> getAllCustomers();
    CustomerDto getCustomerById(int id);
    CustomerDto updateCustomer(CustomerDto customerDto, int id);
    void deleteCustomer(int id);

}
