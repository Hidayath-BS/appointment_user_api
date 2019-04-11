package org.zerhusen.ams.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.zerhusen.ams.model.AmsPatientAuthority;




@Repository
public interface AmsPatientAuthorityRepository extends JpaRepository<AmsPatientAuthority, Integer>{

	public AmsPatientAuthority findById(int id);
	
	public  AmsPatientAuthority findByRole(String role);
}
