package org.zerhusen.ams.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.zerhusen.ams.model.ConsultationRequests;

@Repository
public interface ConsultationRequestRepository extends JpaRepository<ConsultationRequests, Integer>{

	public ConsultationRequests findById(int id);
}
