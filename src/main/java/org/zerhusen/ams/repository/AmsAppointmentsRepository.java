package org.zerhusen.ams.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.zerhusen.ams.model.AmsAppointments;




@Repository
public interface AmsAppointmentsRepository extends JpaRepository<AmsAppointments, Integer> {

	public AmsAppointments findById(int id);
}
