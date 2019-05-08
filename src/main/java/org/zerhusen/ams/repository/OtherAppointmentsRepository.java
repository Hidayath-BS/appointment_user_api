package org.zerhusen.ams.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.zerhusen.ams.model.OtherAppointments;

@Repository
public interface OtherAppointmentsRepository extends JpaRepository<OtherAppointments, Integer>  {

	public OtherAppointments findById(int id);
}
