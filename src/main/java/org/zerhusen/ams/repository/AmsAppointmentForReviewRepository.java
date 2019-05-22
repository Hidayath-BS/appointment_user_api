package org.zerhusen.ams.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.zerhusen.ams.model.AmsAppointments;
import org.zerhusen.ams.model.AmsAppointmentsForReview;


@Repository
public interface AmsAppointmentForReviewRepository extends JpaRepository<AmsAppointmentsForReview, Integer>{

 	public AmsAppointmentsForReview findByAppointment(AmsAppointments appointment);
 	
 	
}
