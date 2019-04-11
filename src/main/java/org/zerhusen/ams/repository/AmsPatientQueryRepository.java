package org.zerhusen.ams.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.zerhusen.ams.model.AmsPatientQueries;




@Repository
public interface AmsPatientQueryRepository extends JpaRepository<AmsPatientQueries, Integer> {
	public AmsPatientQueries findById(int id);
}
