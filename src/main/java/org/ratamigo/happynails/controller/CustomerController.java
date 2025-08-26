//package org.ratamigo.happynails.controller;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api/")
//public class CustomerController {
//
//
//
//    @GetMapping("customers")
//    public ResponseEntity<List<CustomerDto>> getCustomers() {
//        // return new ResponseEntity<>()
//    }
//
//    @GetMapping("customers/{id}")
//    public ResponseEntity<CustomerDto> customerDetail(@PathVariable("id") int customerId) {
//        // return ResponseEntiity.ok()
//    }
//
//    @PostMapping("customers/create")
//    @ResponseStatus(HttpStatus.CREATED)
//    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerDto customerDto) {
//        // return new ResponseEntity<>()
//    }
//
//    @PutMapping("customers/{id}/update")
//    @ResponseStatus(HttpStatus.CREATED)
//    public ResponseEntity<CustomerDto> updateCustomoer(@RequestBody CustomerDto customerDto,
//                                                       @PathVariable("id") int customerId) {
//        // return new ResponseEntity<>()
//    }
//
//    @DeleteMapping("customers/{id}/delete")
//    public ResponseEntity<String> deleteCustomer(@PathVariable("id") int customerId) {
//        //customerService.deleteCustomer(customerId);
//    }
//}
