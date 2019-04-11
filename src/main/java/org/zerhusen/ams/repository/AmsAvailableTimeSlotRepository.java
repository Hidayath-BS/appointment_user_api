package org.zerhusen.ams.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.zerhusen.ams.model.AmsAvailableTimeSlots;



@Repository
public interface AmsAvailableTimeSlotRepository extends JpaRepository<AmsAvailableTimeSlots, Integer> {

	public AmsAvailableTimeSlots findById(int id);
}
