package org.ratamigo.happynails.customers.service;

import org.ratamigo.happynails.customers.dto.CustomerDto;
import org.ratamigo.happynails.customers.dto.CustomerGetAllResponse;


public interface CustomerService {
    CustomerDto createCustomer(CustomerDto customerDto);
    CustomerGetAllResponse getAllCustomers(int pageNo, int pageSize); 
    CustomerDto getCustomerById(int id);
    CustomerDto updateCustomer(CustomerDto customerDto, int id);
    void deleteCustomer(int id);

}
