package org.ratamigo.happynails.controller;

import org.ratamigo.happynails.dto.CustomerDto;
import org.ratamigo.happynails.dto.CustomerGetAllResponse;
import org.ratamigo.happynails.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class CustomerController {
    
    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("customers")
    public ResponseEntity<CustomerGetAllResponse> getCustomers(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
    ) {
        return new ResponseEntity<>(customerService.getAllCustomers(pageNo, pageSize), HttpStatus.OK);
    }

    @GetMapping("customers/{id}")
    public ResponseEntity<CustomerDto> customerDetail(@PathVariable("id") int customerId) {
        return ResponseEntity.ok(customerService.getCustomerById(customerId));
    }

    @PostMapping("customers/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerDto customerDto) {
        return new ResponseEntity<>(customerService.createCustomer(customerDto), HttpStatus.CREATED);
    }

    @PutMapping("customers/{id}/update")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CustomerDto> updateCustomer(@RequestBody CustomerDto customerDto,
                                                       @PathVariable("id") int customerId) {
        return new ResponseEntity<>(customerService.updateCustomer(customerDto, customerId),
                                    HttpStatus.OK);
    }

    @DeleteMapping("customers/{id}/delete")
    public ResponseEntity<String> deleteCustomer(@PathVariable("id") int customerId) {
        customerService.deleteCustomer(customerId);
        return new ResponseEntity<>("Customer deleted", HttpStatus.OK);
    }
}