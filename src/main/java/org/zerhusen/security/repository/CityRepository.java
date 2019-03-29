package org.zerhusen.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.zerhusen.model.security.Ak_city;
import org.zerhusen.model.security.Ak_state;
@Repository
public interface CityRepository extends JpaRepository<Ak_city, Integer> {
	
	Ak_city findById(int id);
	Ak_city findBysateid(int sateid);
	Ak_city findByCity(String city);
	

}
