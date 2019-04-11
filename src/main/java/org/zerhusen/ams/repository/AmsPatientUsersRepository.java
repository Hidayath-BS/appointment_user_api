package org.zerhusen.ams.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.zerhusen.ams.model.Ams_patient_users;




@Repository
public interface AmsPatientUsersRepository extends JpaRepository<Ams_patient_users, Integer> {
	public Ams_patient_users findByEmail(String email);
	
	public Ams_patient_users findById(long id);
	
	public Ams_patient_users findByMobileNumber(String mobileNumber);
	
	@Query("SELECT coalesce(max(ch.id), 0) FROM Ams_patient_users ch")
	Integer getMaxId();
}
