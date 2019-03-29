package org.zerhusen.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerhusen.model.security.Ak_state;

public interface StateRepository extends JpaRepository<Ak_state, Integer> {
	
	Ak_state findById(int id);
	
	Ak_state findByState(String state);
	
}
