package org.zerhusen.ams.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerhusen.ams.model.Ams_Procedures;


public interface AmsProceduresRepository extends JpaRepository<Ams_Procedures, Integer> {
	
	Ams_Procedures findById(int id);

}
