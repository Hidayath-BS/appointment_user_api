package org.zerhusen.ams.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.zerhusen.ams.model.AmsReferralDoctors;


@Repository
public interface AmsReferralRepository extends JpaRepository<AmsReferralDoctors, Integer>{

	
	AmsReferralDoctors findById(int id);
	
	List<AmsReferralDoctors> findByFullName(String fullName);
}
