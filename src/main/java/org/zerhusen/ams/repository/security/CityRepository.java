package org.zerhusen.ams.repository.security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.zerhusen.ams.model.security.Ak_city;



@Repository
public interface CityRepository extends JpaRepository<Ak_city, Integer> {
	
	Ak_city findById(int id);
	Ak_city findBysateid(int sateid);
	Ak_city findByCity(String city);
	

}
