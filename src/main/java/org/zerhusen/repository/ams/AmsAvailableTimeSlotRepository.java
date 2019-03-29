package org.zerhusen.repository.ams;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.zerhusen.model.ams.AmsAvailableTimeSlots;

@Repository
public interface AmsAvailableTimeSlotRepository extends JpaRepository<AmsAvailableTimeSlots, Integer> {

	public AmsAvailableTimeSlots findById(int id);
}
