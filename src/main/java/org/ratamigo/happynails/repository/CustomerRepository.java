package org.ratamigo.happynails.repository;

import org.ratamigo.happynails.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    
}
