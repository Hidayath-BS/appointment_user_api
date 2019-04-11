package org.zerhusen.ams.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.zerhusen.ams.model.AmsTimeSlots;




@Repository
public interface AmsTimeSlotsRepository extends JpaRepository<AmsTimeSlots, Integer> {

	public AmsTimeSlots findById(int id);
}
