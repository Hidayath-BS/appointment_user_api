package org.zerhusen.ams.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.zerhusen.ams.model.AmsReschedules;




@Repository
public interface AmsRescheduleRepository  extends JpaRepository<AmsReschedules, Integer> {

	public AmsReschedules findById(int id);
}
