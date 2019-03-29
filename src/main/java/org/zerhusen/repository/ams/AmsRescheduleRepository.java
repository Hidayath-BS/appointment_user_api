package org.zerhusen.repository.ams;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.zerhusen.model.ams.AmsReschedules;

@Repository
public interface AmsRescheduleRepository  extends JpaRepository<AmsReschedules, Integer> {

	public AmsReschedules findById(int id);
}
