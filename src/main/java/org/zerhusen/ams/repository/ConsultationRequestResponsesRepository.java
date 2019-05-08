package org.zerhusen.ams.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.zerhusen.ams.model.ConsultationRequestResponses;
import org.zerhusen.ams.model.ConsultationRequests;

@Repository
public interface ConsultationRequestResponsesRepository extends JpaRepository<ConsultationRequestResponses, Integer> {

	public ConsultationRequestResponses findById(int id);
	
	public Collection<ConsultationRequestResponses> findByRequest(ConsultationRequests request);
}
