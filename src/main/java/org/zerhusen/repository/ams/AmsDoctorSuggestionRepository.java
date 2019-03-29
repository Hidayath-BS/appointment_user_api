package org.zerhusen.repository.ams;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.zerhusen.model.ams.AmsDoctorSuggestion;

@Repository
public interface AmsDoctorSuggestionRepository extends JpaRepository<AmsDoctorSuggestion, Integer> {

}
