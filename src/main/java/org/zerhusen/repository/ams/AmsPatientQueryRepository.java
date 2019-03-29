package org.zerhusen.repository.ams;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.zerhusen.model.ams.AmsPatientQueries;

@Repository
public interface AmsPatientQueryRepository extends JpaRepository<AmsPatientQueries, Integer> {
	public AmsPatientQueries findById(int id);
}
