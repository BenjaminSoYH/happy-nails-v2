package org.ratamigo.happynails.servicetypes.repository;

import org.ratamigo.happynails.servicetypes.model.ServiceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceTypeRepository extends JpaRepository<ServiceType, Integer>{
    
}
