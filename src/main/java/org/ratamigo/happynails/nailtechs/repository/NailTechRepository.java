package org.ratamigo.happynails.nailtechs.repository;

import java.util.List;

import org.ratamigo.happynails.nailtechs.model.NailTech;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NailTechRepository extends JpaRepository<NailTech, Integer>{
    List<NailTech> findByServicesId(int serviceId);

    // Define a query that we can use in the service
    @Query("""
	SELECT n
	FROM ServiceType AS s, NailTech AS n
	WHERE s MEMBER OF n.services AND s.id IN :serviceList
	GROUP BY n.id
	HAVING COUNT(DISTINCT s.id) >= :length""")
    List<NailTech> findByServices(@Param("serviceList") List<Integer> serviceIds,
                                  @Param("length") int length);
}
