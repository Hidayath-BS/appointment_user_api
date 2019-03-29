package org.zerhusen.repository.ams;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.zerhusen.model.ams.Ams_Services_available;

@Repository
public interface AmsServicesRepository extends JpaRepository<Ams_Services_available, Integer> {
	Ams_Services_available findById(int id);
}
