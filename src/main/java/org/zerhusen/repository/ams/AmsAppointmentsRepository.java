package org.zerhusen.repository.ams;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.zerhusen.model.ams.AmsAppointments;

@Repository
public interface AmsAppointmentsRepository extends JpaRepository<AmsAppointments, Integer> {

	public AmsAppointments findById(int id);
}
