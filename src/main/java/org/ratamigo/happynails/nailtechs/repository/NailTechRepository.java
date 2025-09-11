package org.ratamigo.happynails.nailtechs.repository;

import java.util.List;

import org.ratamigo.happynails.nailtechs.model.NailTech;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NailTechRepository extends JpaRepository<NailTech, Integer>{
    List<NailTech> findByServicesId(int serviceId);
}
