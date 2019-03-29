package org.zerhusen.repository.ams;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.zerhusen.model.ams.AmsPatientAuthority;

@Repository
public interface AmsPatientAuthorityRepository extends JpaRepository<AmsPatientAuthority, Integer>{

	public AmsPatientAuthority findById(int id);
	
	public  AmsPatientAuthority findByRole(String role);
}
