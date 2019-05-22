package org.zerhusen.ams.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.zerhusen.ams.model.AmsPatientConversation;

@Repository
public interface AmsPatientConversationRepository extends JpaRepository<AmsPatientConversation, Integer> {

	
	public AmsPatientConversation findById(int id);
}
