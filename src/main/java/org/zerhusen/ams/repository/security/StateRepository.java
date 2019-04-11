package org.zerhusen.ams.repository.security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerhusen.ams.model.security.Ak_state;




public interface StateRepository extends JpaRepository<Ak_state, Integer> {
	
	Ak_state findById(int id);
	
	Ak_state findByState(String state);
	
}
