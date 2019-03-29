package org.zerhusen.repository.ams;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.zerhusen.model.ams.AmsTimeSlots;

@Repository
public interface AmsTimeSlotsRepository extends JpaRepository<AmsTimeSlots, Integer> {

	public AmsTimeSlots findById(int id);
}
