package org.ratamigo.happynails.service;

import org.ratamigo.happynails.dto.CustomerDto;
import org.ratamigo.happynails.dto.CustomerGetAllResponse;


public interface CustomerService {
    CustomerDto createCustomer(CustomerDto customerDto);
    CustomerGetAllResponse getAllCustomers(int pageNo, int pageSize); 
    CustomerDto getCustomerById(int id);
    CustomerDto updateCustomer(CustomerDto customerDto, int id);
    void deleteCustomer(int id);

}
